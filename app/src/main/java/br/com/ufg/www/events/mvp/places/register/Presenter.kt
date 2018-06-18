package br.com.ufg.www.events.mvp.places.register

import br.com.ufg.www.events.extensions.isValid
import br.com.ufg.www.events.model.Place

class Presenter(private val view: Contract.View) : Contract.Presenter {

    private val interactor = Interactor()

    override fun register(place: Place) {
        val callback = { id: Long -> if (id.isValid()) view.onRegistered() }
        interactor.register(place, callback).start()
    }
}