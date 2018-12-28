package br.com.ufg.www.events.data.offline.interactor

import br.com.ufg.www.events.data.offline.database.AppDatabase
import br.com.ufg.www.events.data.offline.entities.MySkillEntity
import kotlinx.coroutines.coroutineScope

class InteractorMySkills {

    suspend fun delete(idSkill: Long) = coroutineScope { AppDatabase.getInstance().mySkillsDAO().delete(idSkill = idSkill) }
    suspend fun save(idSkill: Long) = coroutineScope { AppDatabase.getInstance().mySkillsDAO().insert(MySkillEntity(idSkill = idSkill)) }

}
