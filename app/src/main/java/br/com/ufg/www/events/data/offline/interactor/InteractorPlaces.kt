package br.com.ufg.www.events.data.offline.interactor

import br.com.ufg.www.events.data.offline.database.AppDatabase
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class InteractorPlaces {

    suspend fun load(userLogin: String) = coroutineScope {
        async { AppDatabase.getInstance().placeDAO().readAll(userLogin = userLogin).map { entity -> entity.toModel() } }
    }

}