package br.com.ufg.www.places.mvp.user.login

import br.com.ufg.www.places.domain.UIFeedback
import br.com.ufg.www.places.model.Login

interface Contract {

    interface View : UIFeedback {
        fun login()
        fun register()
        fun onLoggedIn()
        fun loginFailed()
    }

    interface Presenter {
        fun login(login: Login)
    }

}