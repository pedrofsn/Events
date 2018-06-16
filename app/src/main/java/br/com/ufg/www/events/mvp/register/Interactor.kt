package br.com.ufg.www.events.mvp.register

import br.com.ufg.www.events.database.AppDatabase
import br.com.ufg.www.events.model.Login
import br.com.ufg.www.events.model.RegisterUser
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

class Interactor {

    fun register(registerUser: RegisterUser, callback: ((Long) -> Unit)) = launch(UI) {
        val deferred = async(CommonPool) { AppDatabase.getInstance().userDAO().insert(registerUser.toEntity()) }
        val result = deferred.await()
        callback.invoke(result)
    }

}