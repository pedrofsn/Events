package br.com.ufg.www.events.view.user.login

import br.com.ufg.www.events.database.AppDatabase
import br.com.ufg.www.events.domain.BaseInteractorMVVM
import br.com.ufg.www.events.model.ui.InputLogin
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class InteractorLogin(job: Job) : BaseInteractorMVVM(job) {

    suspend fun login(login: InputLogin) = coroutineScope {
        val entity = login.toEntity()
        return@coroutineScope async {
            AppDatabase.getInstance().userDAO().isLoginValid(
                    email = entity.email,
                    passwordUpperAndHashed = entity.passwordUpperAndHashed
            )
        }
    }

}