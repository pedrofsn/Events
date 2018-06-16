package br.com.ufg.www.events.mvp.user.login

import br.com.ufg.www.events.model.Login

class Presenter(private val view: Contract.View) : Contract.Presenter {

    private val interactor = Interactor()

    override fun login(login: Login) {
        val callback = { result: Boolean ->
            if (result) {
                view.onLoggedIn()
            } else {
                view.loginFailed()
            }
        }

        interactor.login(login, callback).start()
    }
}
