package br.com.ufg.www.events.view.user.login

import br.com.redcode.base.extensions.delay
import br.com.redcode.base.utils.Constants.EMPTY_STRING
import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModelWithLiveData
import br.com.ufg.www.events.App
import br.com.ufg.www.events.data.offline.interactor.InteractorUser
import br.com.ufg.www.events.data.ui.InputLogin
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModelWithLiveData<InputLogin>() {

    private val interactor = InteractorUser()

    override fun load() {
        val temp = InputLogin(EMPTY_STRING)
        temp.login = "pedrokra@gmail.com"
        temp.password = "pedrokra@gmail.com"
        liveData.postValue(temp)
        delay(500) { login() }
    }

    fun login() {
        launch(coroutineContext) {
            liveData.value?.let { login ->
                App.userLoggedIn = interactor.login(login).await()
                val command = if (App.userLoggedIn != null) "onLoggedIn" else "onLoginFailed"
                sendEventToUI(command)
            }
        }
    }

}