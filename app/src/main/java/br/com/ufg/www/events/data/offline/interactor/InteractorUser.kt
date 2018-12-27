package br.com.ufg.www.events.data.offline.interactor

import br.com.ufg.www.events.data.offline.database.AppDatabase
import br.com.ufg.www.events.data.ui.InputLogin
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class InteractorUser {

    suspend fun readAll() = coroutineScope {
        async {
            AppDatabase.getInstance().userDAO().readAll()
        }
    }

    suspend fun getUser(idUser: Long) = coroutineScope {
        async {
            AppDatabase.getInstance().userDAO().getUser(idUser)
        }
    }

    suspend fun login(login: InputLogin) = coroutineScope {
        async {
            val entity = login.toEntity()

            AppDatabase.getInstance().userDAO().login(
                    email = entity.email,
                    passwordUpperAndHashed = entity.passwordUpperAndHashed
            )
        }
    }

}