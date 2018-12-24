package br.com.ufg.www.events.mvp.user.register

import br.com.ufg.www.events.model.ui.InputUser

class InteractorRegisterUser {

    fun register(registerUser: InputUser, callback: ((Long) -> Unit)) {
//        launch(UI) {
//            val deferred = async(CommonPool) { AppDatabase.getInstance().userDAO().insert(registerUser.toEntity()) }
//            val result = deferred.await()
//            callback.invoke(result)
//        }
    }

}