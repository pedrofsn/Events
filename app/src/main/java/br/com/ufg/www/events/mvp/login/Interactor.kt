package br.com.ufg.www.events.mvp.login

import br.com.ufg.www.events.database.AppDatabase
import br.com.ufg.www.events.model.Login

class Interactor {

    fun login(login: Login) = AppDatabase.getInstance().userDAO().isLoginValid(email = login.login, passwordUpperAndHashed = login.password)

}