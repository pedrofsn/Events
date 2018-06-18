package br.com.ufg.www.events.mvp.places.list

import br.com.ufg.www.events.App
import br.com.ufg.www.events.model.Place

class Presenter(private val view: Contract.View) : Contract.Presenter {

    private val interactor = Interactor()

    override fun load() {
        val callback = { places: List<Place> -> view.onLoaded(places) }
        interactor.load(App.userLoggedIn, callback).start()
    }
}
