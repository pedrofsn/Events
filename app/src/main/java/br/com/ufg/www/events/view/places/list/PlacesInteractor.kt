package br.com.ufg.www.events.view.places.list

import br.com.ufg.www.events.domain.BaseInteractorMVVM
import br.com.ufg.www.events.model.Place
import kotlinx.coroutines.Job

class PlacesInteractor(job: Job) : BaseInteractorMVVM(job) {

    fun load(userLogin: String, callback: ((List<Place>) -> Unit)) {
        // TODO
//        launch(job) {
//            val deferred = async(CommonPool) { AppDatabase.getInstance().placeDAO().readAll(userLogin = userLogin).map { entity -> entity.toModel() } }
//            val result = deferred.await()
//            callback.invoke(result)
//        }
    }

}