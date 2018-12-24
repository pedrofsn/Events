package br.com.ufg.www.events.view.user.register

import br.com.ufg.www.events.database.AppDatabase
import br.com.ufg.www.events.model.ui.InputUser
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class InteractorRegisterUser {

    suspend fun register(registerUser: InputUser) = coroutineScope {
        async { AppDatabase.getInstance().userDAO().insert(registerUser.toEntity()) }
    }

}