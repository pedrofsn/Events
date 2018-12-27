package br.com.ufg.www.events.view.places.add_map

import br.com.ufg.www.events.data.model.Place
import com.google.android.gms.maps.model.LatLng

/**
 * Created by pedrofsn on 04/05/18.
 */

// TODO migrate from MVP to MVVM
class Contract {

    interface Presenter {
        fun load(query: String? = null)
        fun save(latLng: LatLng?)
    }

    interface View {
        fun save()
        fun updateUI(list: List<Place>)
        fun askForLocation()
    }

}
