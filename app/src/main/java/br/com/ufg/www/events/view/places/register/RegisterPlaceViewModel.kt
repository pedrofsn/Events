package br.com.ufg.www.events.view.places.register

import br.com.redcode.base.utils.Constants.EMPTY_STRING
import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModelWithLiveData
import br.com.ufg.www.events.App
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
        App.userLoggedIn?.idUser?.let { idUser ->
            liveData.value?.toPlaceEntity(idUser)?.let { entity ->
                interactor.register(entity)
                sendEventToUI("onSuccess")
            }
        }
    }

}