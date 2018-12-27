package br.com.ufg.www.events.data.offline.interactor

import br.com.ufg.www.events.data.offline.database.AppDatabase
import br.com.ufg.www.events.data.ui.InputUser
import kotlinx.coroutines.coroutineScope

class InteractorRegisterUser {

    suspend fun register(registerUser: InputUser) = coroutineScope { AppDatabase.getInstance().userDAO().insert(registerUser.toEntity()) }

}