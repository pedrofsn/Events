package br.com.ufg.www.events.view.places.register

import br.com.redcode.base.extensions.toLogcat
import br.com.redcode.base.utils.Constants.EMPTY_STRING
import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModelWithLiveData
import br.com.ufg.www.events.data.offline.interactor.InteractorRegisterPlace
import br.com.ufg.www.events.data.ui.InputPlace
import kotlinx.coroutines.launch

class RegisterPlaceViewModel : BaseViewModelWithLiveData<InputPlace>() {

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