package br.com.ufg.www.events.view.user.login

import br.com.redcode.base.extensions.delay
import br.com.redcode.base.utils.Constants.EMPTY_STRING
import br.com.ufg.www.events.App
import br.com.ufg.www.events.database.Mock
import br.com.ufg.www.events.domain.BaseViewModelWithLiveData2
import br.com.ufg.www.events.model.ui.InputLogin
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModelWithLiveData2<InputLogin>() {

    private val interactor = InteractorLogin()

    override fun load() {
        val temp = InputLogin(EMPTY_STRING)
        temp.login = "pedrofsn"
        temp.password = "pedrofsn"
        liveData.postValue(temp)
        delay { login() }
    }

    fun login() {
        launch(coroutineContext) {
            liveData.value?.let { login ->
                val loggedIn = interactor.login(login).await()

                Mock.seedDatabase()

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