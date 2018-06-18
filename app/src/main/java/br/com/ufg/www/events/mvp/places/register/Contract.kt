package br.com.ufg.www.events.mvp.places.register

import br.com.ufg.www.events.domain.UIFeedback
import br.com.ufg.www.events.model.Place

interface Contract {

    interface View : UIFeedback {
        fun register()
        fun onRegistered()
    }

    interface Presenter {
        fun register(place: Place)
    }
}