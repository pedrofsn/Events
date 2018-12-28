package br.com.ufg.www.events.data.offline.interactor

import br.com.ufg.www.events.data.offline.database.AppDatabase
import br.com.ufg.www.events.data.offline.entities.EventWithJobTypeEntity
import kotlinx.coroutines.coroutineScope

class InteractorEventWithJobType {

    suspend fun save(entity: EventWithJobTypeEntity) = coroutineScope { AppDatabase.getInstance().eventWithJobTypeDAO().insert(entity) }
    suspend fun insertAll(idEvent: Long, vararg entities: EventWithJobTypeEntity) = coroutineScope { AppDatabase.getInstance().eventWithJobTypeDAO().insertAll(idEvent, *entities) }

}