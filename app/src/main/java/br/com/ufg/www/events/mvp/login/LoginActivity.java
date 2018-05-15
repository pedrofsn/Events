package br.com.ufg.www.events.mvp.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import br.com.ufg.www.events.R;
import br.com.ufg.www.events.domain.BaseActivity;
import br.com.ufg.www.events.model.Login;
import br.com.ufg.www.events.mvp.maps.List_Places_Activity;

public class LoginActivity extends BaseActivity implements Contract.View, View.OnClickListener {

    private Presenter presenter = new Presenter(this);
    private String loginString = "";
    private String passwordString = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button button_loggin = findViewById(R.id.btnLogar);
        button_loggin.setOnClickListener(this);
    }

    @Override
    public void login() {
        Login sing_in = new Login(loginString, passwordString);
        presenter.login(sing_in);
    }

    @Override
    public void onLoggedIn() {
        showMessage("Logou com sucesso!");
        callListPlaces();
    }

    @Override
    public void onClick(View v) {
        hideKeyboard();
        TextView login    = findViewById(R.id.edtLogin);
        TextView password = findViewById(R.id.edtSenha);
        loginString = login.getText().toString();
        passwordString = password.getText().toString();
    }

    public void hideKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void callListPlaces(){
        Intent intent = new Intent(this, List_Places_Activity.class);
    }

    public void loginFailed(){
        showMessage("Usuário ou senhas inválidos");
    }

}
