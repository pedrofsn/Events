package br.com.ufg.www.places.mvp.user.login

import br.com.ufg.www.places.App
import br.com.ufg.www.places.model.Login

class Presenter(private val view: Contract.View) : Contract.Presenter {

    private val interactor = Interactor()

    override fun login(login: Login) {
        val callback = { result: Boolean ->
            if (result) {
                App.userLoggedIn = login.login
                view.onLoggedIn()
            } else {
                view.loginFailed()
            }
        }

        interactor.login(login, callback).start()
    }
}
