package br.com.ufg.www.events.mvp.maps.list

import br.com.ufg.www.events.App
import br.com.ufg.www.events.model.Event

class Presenter(private val view: Contract.View) : Contract.Presenter {

    private val interactor = Interactor()

    override fun load() {
        val callback = { events: List<Event> -> view.onLoaded(events) }
        interactor.load(App.userLoggedIn, callback).start()
    }
}
