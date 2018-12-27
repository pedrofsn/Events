package br.com.ufg.www.events.data.offline.interactor

import br.com.ufg.www.events.data.offline.database.AppDatabase
import br.com.ufg.www.events.data.offline.entities.EventWithJobTypeEntity
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class InteractorEventWithJobType {

    suspend fun save(entity: EventWithJobTypeEntity) = coroutineScope {
        async { AppDatabase.getInstance().eventWithJobTypeDAO().insert(entity) }
    }

}