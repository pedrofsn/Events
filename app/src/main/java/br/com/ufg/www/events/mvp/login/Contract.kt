package br.com.ufg.www.events.mvp.login

import br.com.ufg.www.events.domain.UIFeedback
import br.com.ufg.www.events.model.Login

interface Contract {

    interface View : UIFeedback {
        fun login()
        fun onLoggedIn()
        fun loginFailed()
    }

    interface Presenter {
        fun login(login: Login)
    }

}