package br.com.ufg.www.events.data.offline.interactor

import br.com.ufg.www.events.data.offline.database.AppDatabase
import br.com.ufg.www.events.data.offline.entities.UserEntity
import br.com.ufg.www.events.data.ui.InputLogin
import kotlinx.coroutines.coroutineScope

class InteractorUser {

    suspend fun readAll() = coroutineScope { AppDatabase.getInstance().userDAO().readAll() }
    suspend fun getUser(idUser: Long) = coroutineScope { AppDatabase.getInstance().userDAO().getUser(idUser) }

    suspend fun login(login: InputLogin) = coroutineScope {
        val entity: UserEntity = login.toEntity()

        return@coroutineScope AppDatabase.getInstance().userDAO().login(
                email = entity.email,
                passwordUpperAndHashed = entity.passwordUpperAndHashed
        )
    }

}