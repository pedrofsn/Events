package br.com.ufg.www.places.mvp.user.register

import br.com.ufg.www.places.App
import br.com.ufg.www.places.extensions.isValid
import br.com.ufg.www.places.model.RegisterUser

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