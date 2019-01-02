package br.com.ufg.www.events.view.places.list

import br.com.redcode.base.mvvm.extensions.observer
import br.com.redcode.easyrecyclerview.library.extension_functions.setCustomAdapter
import br.com.redcode.easyrestful.library.fragment.FragmentSwipeRefreshRecyclerViewMVVM
import br.com.ufg.www.events.R
import br.com.ufg.www.events.data.model.Place
import br.com.ufg.www.events.databinding.FragmentPlacesBinding
import br.com.ufg.www.events.view.places.list.adapter.AdapterPlace
import br.com.ufg.www.events.view.places.map.GoogleMapsActivity

class PlacesFragment : FragmentSwipeRefreshRecyclerViewMVVM<FragmentPlacesBinding, PlacesViewModel>() {

    override val colummns = 1
    override val hasSearch = false
    override val hasSwipeRefreshLayout = false
    override val classViewModel = PlacesViewModel::class.java
    override val layout = R.layout.fragment_places

    override val adapter = AdapterPlace() { place: Place, _: Int -> openInMaps(place) }
    private val observer = observer<List<Place>> { updateUI(it) }

    override fun afterOnCreate() {
        binding.recyclerView.setCustomAdapter(adapter)
        binding.fab.setOnClickListener { addSample() }
    }

    override fun setupUI() {
        super.setupUI()
        viewModel.myList.observe(this, observer)
    }

    private fun updateUI(items: List<Place>) {
        adapter.setDiffList(items)
    }

    override fun onResume() {
        super.onResume()
        viewModel.load()
    }

    private fun addSample() = viewModel.addSample()

    private fun openInMaps(place: Place) = goTo<GoogleMapsActivity>("place" to place)

}
