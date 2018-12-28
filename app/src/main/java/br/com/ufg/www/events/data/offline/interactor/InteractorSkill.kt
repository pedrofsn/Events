package br.com.ufg.www.events.data.offline.interactor

import br.com.ufg.www.events.data.offline.database.AppDatabase
import kotlinx.coroutines.coroutineScope

class InteractorSkill {

    suspend fun readAll() = coroutineScope { AppDatabase.getInstance().skillDAO().readAll() }
    suspend fun readAll(idEvent: Long) = coroutineScope { AppDatabase.getInstance().skillDAO().readAll(idEvent) }
    suspend fun getMySkills() = coroutineScope { AppDatabase.getInstance().skillDAO().getMySkills() }

}
