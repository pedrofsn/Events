package br.com.ufg.www.events.data.offline.interactor

import br.com.ufg.www.events.data.model.Place
import br.com.ufg.www.events.data.offline.database.AppDatabase
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class InteractorRegisterPlace {

    suspend fun register(place: Place) = coroutineScope {
        async { AppDatabase.getInstance().placeDAO().insert(place.toEntity()) }
    }

}