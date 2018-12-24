package br.com.ufg.www.events.view.places.register

import br.com.redcode.base.extensions.toLogcat
import br.com.redcode.base.utils.Constants.EMPTY_STRING
import br.com.ufg.www.events.domain.BaseViewModelWithLiveData2
import br.com.ufg.www.events.model.ui.InputPlace
import kotlinx.coroutines.launch

class RegisterPlaceViewModel : BaseViewModelWithLiveData2<InputPlace>() {

    private val interactor = InteractorRegisterPlace()

    init {
        load()
    }

    override fun load() = liveData.postValue(InputPlace(EMPTY_STRING))

    fun register() = launch(coroutineContext) {
        liveData.value?.toModel()?.let { place ->
            val id = interactor.register(place).await()

            "Registrado como $id ($place)".toLogcat()
            sendEventToUI("onSuccess")
        }
    }

}