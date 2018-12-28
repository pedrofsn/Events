package br.com.ufg.www.events.data.offline.interactor

import br.com.ufg.www.events.data.offline.database.AppDatabase
import br.com.ufg.www.events.data.offline.entities.PlaceEntity
import kotlinx.coroutines.coroutineScope

class InteractorRegisterPlace {

    suspend fun register(entity: PlaceEntity) = coroutineScope { AppDatabase.getInstance().placeDAO().insert(entity) }

}