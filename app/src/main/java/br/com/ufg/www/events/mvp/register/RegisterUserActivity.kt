package br.com.ufg.www.events.mvp.register

import android.os.Bundle
import android.view.View
import br.com.ufg.www.events.R
import br.com.ufg.www.events.domain.BaseActivity

class RegisterUserActivity : BaseActivity(), Contract.View, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)
        buttonRegister.setOnClickListener(this)
    }

    override fun register() {
        if (editTextLogin.isFilled() && editTextPassword.isFilled() && editTextPasswordConfirmation.isFilled()) {
            val login = editTextLogin.getString()
            val password = editTextPassword.getString()
            val passwordConfirmation = editTextPasswordConfirmation.getString()


        }
    }

    override fun onClick(v: View?) {
        v?.let {
            when (v.id) {
                buttonRegister.id -> register()
            }
        }
    }
}
