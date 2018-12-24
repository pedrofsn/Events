package br.com.ufg.www.events.mvp.user.login

import br.com.ufg.www.events.domain.BaseInteractorMVVM
import br.com.ufg.www.events.model.Login
import kotlinx.coroutines.Job

class InteractorLogin(job: Job) : BaseInteractorMVVM(job) {

    fun login(login: Login, callback: ((Boolean) -> Unit)) {
//        launch(UI) {
//            val entity = login.toEntity()
//            val deferred = async(CommonPool) {
//                AppDatabase.getInstance().userDAO().isLoginValid(
//                        email = entity.email,
//                        passwordUpperAndHashed = entity.passwordUpperAndHashed
//                )
//            }
//            val result = deferred.await()
//            callback.invoke(result)
//        }
    }

}