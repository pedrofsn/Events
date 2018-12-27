package br.com.ufg.www.events.view.user.login

import br.com.redcode.base.extensions.delay
import br.com.redcode.base.utils.Constants.EMPTY_STRING
import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModelWithLiveData
import br.com.ufg.www.events.App
import br.com.ufg.www.events.data.offline.interactor.InteractorLogin
import br.com.ufg.www.events.data.ui.InputLogin
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModelWithLiveData<InputLogin>() {

    private val interactor = InteractorLogin()

    override fun load() {
        val temp = InputLogin(EMPTY_STRING)
        temp.login = "pedrofsn"
        temp.password = "pedrofsn"
        liveData.postValue(temp)
        delay(500) { login() }
    }

    fun login() {
        launch(coroutineContext) {
            liveData.value?.let { login ->

                val loggedIn = interactor.login(login).await()

                val command = if (loggedIn) {
                    App.userLoggedIn = login.login
                    "onLoggedIn"
                } else {
                    "onLoginFailed"
                }

                sendEventToUI(command)
            }
        }
    }

}