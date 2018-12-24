package br.com.ufg.www.events.view.places.register

import br.com.ufg.www.events.database.AppDatabase
import br.com.ufg.www.events.model.Place
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class InteractorRegisterPlace {

    suspend fun register(place: Place) = coroutineScope {
        async { AppDatabase.getInstance().placeDAO().insert(place.toEntity()) }
    }

}