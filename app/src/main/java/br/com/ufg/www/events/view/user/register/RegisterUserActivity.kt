package br.com.ufg.www.events.view.user.register

import android.view.View
import br.com.ufg.www.events.R
import br.com.ufg.www.events.databinding.ActivityRegisterUserBinding
import br.com.ufg.www.events.domain.ActivityMVVM2
import br.com.ufg.www.events.extensions.isFilled
import br.com.ufg.www.events.view.places.list.PlacesActivity

class RegisterUserActivity : ActivityMVVM2<ActivityRegisterUserBinding, RegisterViewModel>() {

    override val classViewModel = RegisterViewModel::class.java
    override val layout = R.layout.activity_register_user

    override fun afterOnCreate() = viewModel.load()

    fun register(view: View?) {
        if (binding.editTextLogin.isFilled() && binding.editTextPassword.isFilled() && binding.editTextPasswordConfirmation.isFilled()) {
            viewModel.register()
        }
    }

    override fun handleEvent(event: String, obj: Any?) {
        when (event) {
            "onRegistered" -> onRegistered()
            else -> super.handleEvent(event, obj)
        }
    }

    private fun onRegistered() = goToWithNoHistory(PlacesActivity::class.java)

}
