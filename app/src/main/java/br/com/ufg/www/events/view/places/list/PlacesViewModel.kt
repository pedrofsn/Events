package br.com.ufg.www.events.view.places.list

import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModelWithLiveData
import br.com.ufg.www.events.App
import br.com.ufg.www.events.data.offline.interactor.InteractorPlace
import br.com.ufg.www.events.data.ui.LabelPlaces
import kotlinx.coroutines.launch

class PlacesViewModel : BaseViewModelWithLiveData<LabelPlaces>() {

    private val interactor = InteractorPlace()

    override fun load() {
        launch(coroutineContext) {
            val places = interactor.load(App.userLoggedIn?.id!!)
            liveData.postValue(LabelPlaces(places))
        }
    }
}