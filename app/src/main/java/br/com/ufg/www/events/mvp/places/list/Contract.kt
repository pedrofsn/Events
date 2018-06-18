package br.com.ufg.www.events.mvp.places.list

import br.com.ufg.www.events.domain.UIFeedback
import br.com.ufg.www.events.model.Place

interface Contract {

    interface View : UIFeedback {
        fun load()
        fun register()
        fun openInMaps(place: Place)
        fun onLoaded(places: List<Place>)
    }

    interface Presenter {
        fun load()
    }

}