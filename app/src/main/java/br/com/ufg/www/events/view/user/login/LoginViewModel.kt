package br.com.ufg.www.events.view.user.login

import br.com.redcode.base.utils.Constants.EMPTY_STRING
import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModelWithLiveData
import br.com.ufg.www.events.model.ui.InputLogin

class LoginViewModel : BaseViewModelWithLiveData<InputLogin>() {

    private val interactor = InteractorLogin(job)

    override fun load() = liveData.postValue(InputLogin(EMPTY_STRING))

    fun login() {
        liveData.value?.let { login ->
            val callback = { result: Boolean ->
                val command = if (result) {
//                    App.userLoggedIn = login
                    "onLoggedIn"
                } else {
                    "onLoginFailed"
                }

                sendEventToUI(command)
            }

//            interactor.login(login, callback)
        }
    }

}