package br.com.ufg.www.events.data.offline.interactor

import br.com.ufg.www.events.data.offline.database.AppDatabase
import br.com.ufg.www.events.data.offline.entities.EventEntity
import kotlinx.coroutines.coroutineScope

class InteractorEvent {

    suspend fun save(entity: EventEntity) = coroutineScope { AppDatabase.getInstance().eventDAO().insert(entity) }
    suspend fun readAll(idUser: Long) = coroutineScope { AppDatabase.getInstance().eventDAO().readAll(idUser) }
    suspend fun read(idEvent: Long) = coroutineScope { AppDatabase.getInstance().eventDAO().read(idEvent) }

}