package br.com.ufg.www.events.view.places.list

import br.com.redcode.base.mvvm.extensions.observer
import br.com.redcode.easyrecyclerview.library.extension_functions.setCustomAdapter
import br.com.redcode.easyrestful.library.fragment.FragmentSwipeRefreshRecyclerViewMVVM
import br.com.ufg.www.events.R
import br.com.ufg.www.events.data.model.Place
import br.com.ufg.www.events.data.ui.LabelPlaces
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
    private val observer = observer<LabelPlaces> { updateUI(it) }

    override fun afterOnCreate() {
        binding.recyclerView.setCustomAdapter(adapter)
        binding.fab.setOnClickListener { showMessage("teste") }
    }

    override fun setupUI() {
        super.setupUI()
        viewModel.liveData.observe(this, observer)
    }

    private fun updateUI(label: LabelPlaces) = adapter.setCustomList(label.items)

    override fun onResume() {
        super.onResume()
        viewModel.load()
    }

    private fun openInMaps(place: Place) = goTo<GoogleMapsActivity>("place" to place)

}
