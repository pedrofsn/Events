package br.com.ufg.www.places.mvp.places.list

import br.com.ufg.www.places.domain.UIFeedback
import br.com.ufg.www.places.model.Place

interface Contract {

    interface View : UIFeedback {
        fun load()
        fun openInMaps(place: Place)
        fun onLoaded(places: List<Place>)
    }

    interface Presenter {
        fun load()
    }

}