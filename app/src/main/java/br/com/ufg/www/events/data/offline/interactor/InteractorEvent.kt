package br.com.ufg.www.events.data.offline.interactor

import br.com.ufg.www.events.data.offline.database.AppDatabase
import br.com.ufg.www.events.data.offline.entities.EventEntity
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class InteractorEvent {

    suspend fun save(entity: EventEntity) = coroutineScope {
        async { AppDatabase.getInstance().eventDAO().insert(entity) }
    }

    suspend fun readAll(idUser: Long) = coroutineScope {
        async { AppDatabase.getInstance().eventDAO().readAll(idUser) }
    }

}