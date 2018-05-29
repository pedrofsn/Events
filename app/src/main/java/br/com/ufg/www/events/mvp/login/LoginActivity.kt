package br.com.ufg.www.events.mvp.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import br.com.ufg.www.events.R
import br.com.ufg.www.events.domain.BaseActivity
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
        val loginString = editTextLogin.text.toString()
        val passwordString = editTextPassword.text.toString()

        val sing_in = Login(loginString, passwordString)
        presenter.login(sing_in)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnLogar -> login()
        }
    }

    override fun onLoggedIn() {
        val intent = Intent(this, List_Places_Activity::class.java)
        startActivity(intent)
    }

    override fun loginFailed() {
        showMessage("Usuário ou senhas inválidos")
    }

}
