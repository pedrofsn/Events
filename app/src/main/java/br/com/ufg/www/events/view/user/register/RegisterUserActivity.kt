package br.com.ufg.www.events.view.user.register

import android.view.View
import br.com.redcode.easyrestful.library.impl.activity.ActivityMVVM
import br.com.redcode.easyvalidation.Validate
import br.com.ufg.www.events.R
import br.com.ufg.www.events.databinding.ActivityRegisterUserBinding
import br.com.ufg.www.events.view.user.professional.ProfessionalActivity

class RegisterUserActivity : ActivityMVVM<ActivityRegisterUserBinding, RegisterViewModel>() {

    override val classViewModel = RegisterViewModel::class.java
    override val layout = R.layout.activity_register_user

    override fun afterOnCreate() = viewModel.load()

    fun register(view: View?) {
        if (Validate.isFilled(binding.editTextLogin, binding.editTextPassword, binding.editTextPasswordConfirmation)) {
            viewModel.register()
        }
    }

    override fun handleEvent(event: String, obj: Any?) {
        when (event) {
            "onRegistered" -> onRegistered()
            else -> super.handleEvent(event, obj)
        }
    }

    private fun onRegistered() = goToWithNoHistory(ProfessionalActivity::class.java)

}
