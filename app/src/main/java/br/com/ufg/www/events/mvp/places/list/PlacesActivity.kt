package br.com.ufg.www.events.mvp.places.list

import android.view.View
import br.com.redcode.base.mvvm.extensions.observer
import br.com.redcode.easyrestful.library.impl.activity.ActivityMVVM
import br.com.ufg.www.events.R
import br.com.ufg.www.events.databinding.ActivityPlacesBinding
import br.com.ufg.www.events.model.Place
import br.com.ufg.www.events.model.ui.LabelPlaces
import br.com.ufg.www.events.mvp.places.PlacesViewModel
import br.com.ufg.www.events.mvp.places.list.adapter.AdapterPlace
import br.com.ufg.www.events.mvp.places.map.GoogleMapsActivity
import br.com.ufg.www.events.mvp.places.register.RegisterPlaceActivity

class PlacesActivity : ActivityMVVM<ActivityPlacesBinding, PlacesViewModel>() {

    override val classViewModel = PlacesViewModel::class.java
    override val layout = R.layout.activity_places

    private val adapter = AdapterPlace() { place: Place, _: Int -> openInMaps(place) }
    private val observer = observer<LabelPlaces> { updateUI(it) }

    override fun afterOnCreate() {

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

    fun register(view: View?) = goTo<RegisterPlaceActivity>()
    private fun openInMaps(place: Place) = goTo<GoogleMapsActivity>("place" to place)

}
