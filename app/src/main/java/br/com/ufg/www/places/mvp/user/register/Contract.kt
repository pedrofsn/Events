package br.com.ufg.www.places.mvp.user.register

import br.com.ufg.www.places.domain.UIFeedback
import br.com.ufg.www.places.model.RegisterUser

interface Contract {

    interface View : UIFeedback {
        fun register()
        fun onRegistered()
    }

    interface Presenter {
        fun register(registerUser: RegisterUser)
    }

}