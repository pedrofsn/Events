package br.com.ufg.www.events.data.offline.interactor

import br.com.ufg.www.events.data.offline.database.AppDatabase
import br.com.ufg.www.events.data.ui.InputLogin
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class InteractorLogin {

    suspend fun readAll() = coroutineScope {
        async {
            AppDatabase.getInstance().userDAO().readAll()
        }
    }

    suspend fun login(login: InputLogin) = coroutineScope {
        async {
            val entity = login.toEntity()

            AppDatabase.getInstance().userDAO().isLoginValid(
                    email = entity.email,
                    passwordUpperAndHashed = entity.passwordUpperAndHashed
            )
        }
    }

}