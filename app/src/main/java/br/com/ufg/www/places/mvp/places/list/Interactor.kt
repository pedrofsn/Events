package br.com.ufg.www.places.mvp.places.list

import br.com.ufg.www.places.database.AppDatabase
import br.com.ufg.www.places.model.Place
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

class Interactor {

    fun load(userLogin: String, callback: ((List<Place>) -> Unit)) = launch(UI) {
        val deferred = async(CommonPool) { AppDatabase.getInstance().placeDAO().readAll(userLogin = userLogin).map { entity -> entity.toModel() } }
        val result = deferred.await()
        callback.invoke(result)
    }

}