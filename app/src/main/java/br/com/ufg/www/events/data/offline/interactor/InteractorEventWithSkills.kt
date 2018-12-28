package br.com.ufg.www.events.data.offline.interactor

import br.com.ufg.www.events.data.offline.database.AppDatabase
import br.com.ufg.www.events.data.offline.entities.EventWithSkillsEntity
import kotlinx.coroutines.coroutineScope

class InteractorEventWithSkills {

    suspend fun save(entity: EventWithSkillsEntity) = coroutineScope { AppDatabase.getInstance().eventWithSkillsDAO().insert(entity) }
    suspend fun insertAll(idEvent: Long, vararg entities: EventWithSkillsEntity) = coroutineScope { AppDatabase.getInstance().eventWithSkillsDAO().insertAll(idEvent, *entities) }

}