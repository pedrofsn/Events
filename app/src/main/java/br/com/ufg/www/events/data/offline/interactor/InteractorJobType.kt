package br.com.ufg.www.events.data.offline.interactor

import br.com.ufg.www.events.data.offline.database.AppDatabase
import kotlinx.coroutines.coroutineScope

class InteractorJobType {

    suspend fun readAll() = coroutineScope { AppDatabase.getInstance().jobTypesDAO().readAll() }
    suspend fun readAll(idEvent: Long) = coroutineScope { AppDatabase.getInstance().jobTypesDAO().readAll(idEvent) }

}
