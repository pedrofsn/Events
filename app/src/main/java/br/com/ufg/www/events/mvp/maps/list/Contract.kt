package br.com.ufg.www.events.mvp.maps.list

import br.com.ufg.www.events.domain.UIFeedback
import br.com.ufg.www.events.model.Event

interface Contract {

    interface View : UIFeedback {
        fun load()
        fun openInMaps(event: Event)
        fun onLoaded(events: List<Event>)
    }

    interface Presenter {
        fun load()
    }

}