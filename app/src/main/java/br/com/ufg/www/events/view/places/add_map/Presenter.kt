package br.com.ufg.www.events.view.places.add_map

import br.com.redcode.base.utils.Constants.EMPTY_STRING
import br.com.ufg.www.events.model.Place
import com.google.android.gms.maps.model.LatLng

/**
 * Created by pedrofsn on 04/05/18.
 */
class Presenter(val view: Contract.View) : Contract.Presenter {

//    private val interactorPlaces = InteractorPlacesGoogle(view)

    var stringLocation: String = EMPTY_STRING

    override fun load(query: String?) {
        if (stringLocation.isBlank()) {
            view.askForLocation()
        } else {

//            view.showProgress()

            val location: String = stringLocation

            val callback = { results: List<Place> ->
                view.updateUI(results)
//                view.hideProgress()
            }

//            val request = interactorPlaces.receivePlaces(query, location, callback)
//            addDisposable(request)
        }
    }

    override fun save(latLng: LatLng?) {
//        view.showMessage(latLng.toString())
    }
}