package br.com.ufg.www.events.data.offline.interactor

import br.com.ufg.www.events.data.offline.database.AppDatabase
import br.com.ufg.www.events.data.offline.entities.PlaceEntity
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class InteractorPlace {

    suspend fun load(idUser: Long) = coroutineScope {
        async { AppDatabase.getInstance().placeDAO().readAll(idUser) }
    }

    suspend fun save(placeEntity: PlaceEntity) = coroutineScope {
        async { AppDatabase.getInstance().placeDAO().insert(placeEntity) }
    }

}