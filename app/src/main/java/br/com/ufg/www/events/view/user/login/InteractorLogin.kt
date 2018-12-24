package br.com.ufg.www.events.view.user.login

import br.com.ufg.www.events.database.AppDatabase
import br.com.ufg.www.events.model.ui.InputLogin
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class InteractorLogin {

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