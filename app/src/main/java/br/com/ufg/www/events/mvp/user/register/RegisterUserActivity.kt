package br.com.ufg.www.events.mvp.user.register

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import android.view.View
import br.com.ufg.www.events.R
import br.com.ufg.www.events.domain.BaseActivity
import br.com.ufg.www.events.extensions.getString
import br.com.ufg.www.events.extensions.isFilled
import br.com.ufg.www.events.model.RegisterUser
import br.com.ufg.www.events.mvp.places.list.PlacesActivity
import kotlinx.android.synthetic.main.activity_register_user.*

class RegisterUserActivity : BaseActivity(), Contract.View, View.OnClickListener {

    private val presenter by lazy { Presenter(this) }
    private val MINIMAL_LENGTH_PASSWORD = 3

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

            when {
                password.length < MINIMAL_LENGTH_PASSWORD -> showMessage(String.format(getString(R.string.passwords_need_minimal_x_characters), MINIMAL_LENGTH_PASSWORD))
                password != passwordConfirmation -> showMessage(getString(R.string.password_doesnt_match))
                else -> {
                    val registerUser = RegisterUser(
                            login = login,
                            password = password
                    )

                    presenter.register(registerUser)
                }
            }

        }
    }

    override fun onRegistered() {
        val intent = Intent(this, PlacesActivity::class.java)
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun onClick(v: View?) {
        v?.let {
            when (v.id) {
                buttonRegister.id -> register()
            }
        }
    }
}
