package br.com.ufg.www.events.view.user.login

import android.view.View
import br.com.redcode.easyrestful.library.impl.activity.ActivityMVVM
import br.com.ufg.www.events.R
import br.com.ufg.www.events.databinding.ActivityLoginBinding
import br.com.ufg.www.events.extensions.isFilled
import br.com.ufg.www.events.view.places.list.PlacesActivity
import br.com.ufg.www.events.view.user.register.RegisterUserActivity

class LoginActivity : ActivityMVVM<ActivityLoginBinding, LoginViewModel>() {

    override val classViewModel = LoginViewModel::class.java
    override val layout = R.layout.activity_login

    override fun afterOnCreate() = viewModel.load()

    fun login(view: View?) {
        if (binding.editTextLogin.isFilled() && binding.editTextPassword.isFilled()) {
            viewModel.login()
        }
    }

    override fun handleEvent(event: String, obj: Any?) {
        when (event) {
            "onLoggedIn" -> onLoggedIn()
            "onLoginFailed" -> onLoginFailed()
            else -> super.handleEvent(event, obj)
        }
    }

    fun register(view: View?) = goTo<RegisterUserActivity>()
    private fun onLoggedIn() = goToWithNoHistory(PlacesActivity::class.java)
    private fun onLoginFailed() = showMessage(getString(R.string.error_login_or_password))

}
