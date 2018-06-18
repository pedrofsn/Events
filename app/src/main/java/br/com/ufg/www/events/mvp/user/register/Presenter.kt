package br.com.ufg.www.events.mvp.user.register

import br.com.ufg.www.events.App
import br.com.ufg.www.events.extensions.isValid
import br.com.ufg.www.events.model.RegisterUser

class Presenter(private val view: Contract.View) : Contract.Presenter {

    private val interactor = Interactor()

    override fun register(registerUser: RegisterUser) {
        val callback = { id: Long ->
            if (id.isValid()) {
                App.userLoggedIn = registerUser.login
                view.onRegistered()
            }
        }

        interactor.register(registerUser, callback).start()
    }
}