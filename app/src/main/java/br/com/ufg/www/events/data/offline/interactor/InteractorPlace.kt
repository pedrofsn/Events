package br.com.ufg.www.events.data.offline.interactor

import br.com.ufg.www.events.data.offline.database.AppDatabase
import br.com.ufg.www.events.data.offline.entities.PlaceEntity
import kotlinx.coroutines.coroutineScope


class InteractorPlace {

    suspend fun readAll(idUser: Long) = coroutineScope { AppDatabase.getInstance().placeDAO().readAll(idUser) }
    suspend fun save(placeEntity: PlaceEntity) = coroutineScope { AppDatabase.getInstance().placeDAO().insert(placeEntity) }
    suspend fun read(id: Long) = coroutineScope { AppDatabase.getInstance().placeDAO().read(id) }

}