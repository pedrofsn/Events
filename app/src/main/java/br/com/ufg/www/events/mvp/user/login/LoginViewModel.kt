package br.com.ufg.www.events.mvp.user.login

import br.com.redcode.base.utils.Constants.EMPTY_STRING
import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModelWithLiveData
import br.com.ufg.www.events.model.Login

class LoginViewModel : BaseViewModelWithLiveData<Login>() {

    private val interactor = InteractorLogin(job)

    init {
        load()
    }

    override fun load() = liveData.postValue(Login(EMPTY_STRING))

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