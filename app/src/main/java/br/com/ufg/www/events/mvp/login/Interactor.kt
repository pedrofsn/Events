package br.com.ufg.www.events.mvp.login

import br.com.ufg.www.events.database.AppDatabase
import br.com.ufg.www.events.model.Login
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

class Interactor {

    fun login(login: Login, callback: ((Boolean) -> Unit)) = launch(UI) {
        val deferred = async(CommonPool) { AppDatabase.getInstance().userDAO().isLoginValid(email = login.login, passwordUpperAndHashed = login.password) }
        val result = deferred.await()
        callback.invoke(result)
    }

}