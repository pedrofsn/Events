package br.com.ufg.www.places.mvp.places.register

import br.com.ufg.www.places.domain.UIFeedback
import br.com.ufg.www.places.model.Place

interface Contract {

    interface View : UIFeedback {
        fun register()
        fun onRegistered()
    }

    interface Presenter {
        fun register(place: Place)
    }
}