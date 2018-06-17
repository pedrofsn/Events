package br.com.ufg.www.events.mvp.maps.list

import br.com.ufg.www.events.database.AppDatabase
import br.com.ufg.www.events.model.Event
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

class Interactor {

    fun load(userLogin: String, callback: ((List<Event>) -> Unit)) = launch(UI) {
        val deferred = async(CommonPool) { AppDatabase.getInstance().eventDAO().readAll(userLogin = userLogin).map { entity -> entity.toModel() } }
        val result = deferred.await()
        callback.invoke(result)
    }

}