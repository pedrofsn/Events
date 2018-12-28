package br.com.ufg.www.events.view.user.register

import android.view.View
import br.com.redcode.easyrestful.library.impl.activity.ActivityMVVM
import br.com.ufg.www.events.R
import br.com.ufg.www.events.databinding.ActivityRegisterUserBinding
import br.com.ufg.www.events.extensions.isFilled
import br.com.ufg.www.events.view.event.list.EventsActivity

class RegisterUserActivity : ActivityMVVM<ActivityRegisterUserBinding, RegisterViewModel>() {

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

    private fun onRegistered() = goToWithNoHistory(EventsActivity::class.java)

}
