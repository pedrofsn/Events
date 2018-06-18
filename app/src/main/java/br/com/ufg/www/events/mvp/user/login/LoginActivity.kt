package br.com.ufg.www.events.mvp.user.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import br.com.ufg.www.events.R
import br.com.ufg.www.events.domain.BaseActivity
import br.com.ufg.www.events.extensions.getString
import br.com.ufg.www.events.extensions.isFilled
import br.com.ufg.www.events.model.Login
import br.com.ufg.www.events.mvp.places.list.PlacesActivity
import br.com.ufg.www.events.mvp.user.register.RegisterUserActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), Contract.View, View.OnClickListener {

    private val presenter = Presenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        buttonLogin.setOnClickListener(this)
        textViewRegister.setOnClickListener(this)
    }

    override fun login() {
        val login = editTextLogin.getString()
        val password = editTextPassword.getString()

        if (editTextLogin.isFilled() && editTextPassword.isFilled()) {
            val obj = Login(login, password)
            presenter.login(obj)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.buttonLogin -> login()
            R.id.textViewRegister -> register()
        }
    }

    override fun register() = startActivity(Intent(this, RegisterUserActivity::class.java))

    override fun onLoggedIn() {
        val intent = Intent(this, PlacesActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun loginFailed() = showMessage(getString(R.string.error_login_or_password))

}
