package br.com.ufg.www.events.mvp.places.register

import br.com.ufg.www.events.domain.BaseInteractorMVVM
import br.com.ufg.www.events.model.Place
import kotlinx.coroutines.Job

class RegisterPlaceInteractor(job: Job) : BaseInteractorMVVM(job) {

    fun register(place: Place, callback: ((Long) -> Unit)) {
        // TODO
//        launch(UI) {
//            val deferred = async(CommonPool) { AppDatabase.getInstance().placeDAO().insert(place.toEntity()) }
//            val result = deferred.await()
//            callback.invoke(result)
//        }
    }

}