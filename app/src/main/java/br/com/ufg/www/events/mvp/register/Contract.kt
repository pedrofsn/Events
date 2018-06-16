package br.com.ufg.www.events.mvp.register

import br.com.ufg.www.events.domain.UIFeedback
import br.com.ufg.www.events.model.RegisterUser

interface Contract {

    interface View : UIFeedback {
        fun register()
        fun onRegistered()
    }

    interface Presenter {
        fun register(registerUser: RegisterUser)
    }

}