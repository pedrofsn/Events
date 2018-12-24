package br.com.ufg.www.events.view.places.register

import br.com.redcode.base.utils.Constants.EMPTY_STRING
import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModelWithLiveData
import br.com.ufg.www.events.model.ui.InputPlace

class RegisterPlaceViewModel : BaseViewModelWithLiveData<InputPlace>() {

    private val interactor = RegisterPlaceInteractor(job)

    init {
        load()
    }

    override fun load() = liveData.postValue(InputPlace(EMPTY_STRING))

    fun register() {
        liveData.value?.toModel()?.let {
            interactor.register(it) {
                // TODO
                showSimpleAlert(it.toString())
                sendEventToUI("onSuccess")
            }
        }
    }

}