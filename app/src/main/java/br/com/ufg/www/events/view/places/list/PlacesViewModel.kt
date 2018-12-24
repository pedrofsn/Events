package br.com.ufg.www.events.view.places.list

import br.com.ufg.www.events.App
import br.com.ufg.www.events.domain.BaseViewModelWithLiveData2
import br.com.ufg.www.events.model.ui.LabelPlaces
import kotlinx.coroutines.launch

class PlacesViewModel : BaseViewModelWithLiveData2<LabelPlaces>() {

    private val interactor = InteractorPlaces()

    override fun load() {
        launch(coroutineContext) {
            val places = interactor.load(App.userLoggedIn).await()
            liveData.postValue(LabelPlaces(places))
        }
    }
}