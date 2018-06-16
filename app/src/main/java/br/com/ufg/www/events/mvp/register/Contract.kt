package br.com.ufg.www.events.mvp.register

import br.com.ufg.www.events.domain.UIFeedback

interface Contract {

    interface View : UIFeedback {
        fun register()
    }

    interface Presenter {
        fun register()
    }

}