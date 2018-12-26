package br.com.ufg.www.events.view.event

import br.com.ufg.www.events.database.AppDatabase
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class InteractorJobTypes {

    suspend fun getJobTypes() = coroutineScope {
        async { AppDatabase.getInstance().jobTypesDAO().readAll().map { entity -> entity.toModel() } }
    }

}
