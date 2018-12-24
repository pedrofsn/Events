package br.com.ufg.www.events.mvp.places

import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModelWithLiveData
import br.com.ufg.www.events.App
import br.com.ufg.www.events.model.Place
import br.com.ufg.www.events.model.ui.LabelPlaces
import br.com.ufg.www.events.mvp.places.list.Interactor

class PlacesViewModel : BaseViewModelWithLiveData<LabelPlaces>() {

    private val interactor = Interactor(job)

    override fun load() {
        val callback = { places: List<Place> -> liveData.postValue(LabelPlaces(places)) }
        interactor.load(App.userLoggedIn, callback)
    }

}