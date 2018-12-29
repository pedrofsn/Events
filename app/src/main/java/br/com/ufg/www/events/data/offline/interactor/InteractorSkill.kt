package br.com.ufg.www.events.data.offline.interactor

import br.com.ufg.www.events.data.offline.database.AppDatabase
import kotlinx.coroutines.coroutineScope

class InteractorSkill {

    suspend fun getAllSkills() = coroutineScope { AppDatabase.getInstance().skillDAO().getAllSkills() }
    suspend fun getSkillsSelectedsAndUnselecteds(idEvent: Long) = coroutineScope { AppDatabase.getInstance().skillDAO().getSkillsSelectedsAndUnselecteds(idEvent) }
    suspend fun getMySkills() = coroutineScope { AppDatabase.getInstance().skillDAO().getMySkills() }

}
