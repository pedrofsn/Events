package br.com.ufg.www.events.mvp.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import br.com.ufg.www.events.R
import br.com.ufg.www.events.domain.BaseActivity
import br.com.ufg.www.events.extensions.getString
import br.com.ufg.www.events.model.Login
import br.com.ufg.www.events.mvp.maps.List_Places_Activity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), Contract.View, View.OnClickListener {

    private val presenter = Presenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        buttonLogin.setOnClickListener(this)
    }

    override fun login() {
        val loginString = editTextLogin.getString()
        val passwordString = editTextPassword.getString()

        val login = Login(loginString, passwordString)
        presenter.login(login)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.buttonLogin -> login()
        }
    }

    override fun onLoggedIn() = startActivity(Intent(this, List_Places_Activity::class.java))

    override fun loginFailed() = showMessage(getString(R.string.error_login_or_password))

}
