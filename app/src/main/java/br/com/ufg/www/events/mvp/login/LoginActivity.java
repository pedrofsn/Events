package br.com.ufg.www.events.mvp.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import br.com.ufg.www.events.R;
import br.com.ufg.www.events.domain.BaseActivity;
import br.com.ufg.www.events.model.Login;
import br.com.ufg.www.events.mvp.maps.List_Places_Activity;

public class LoginActivity extends BaseActivity implements Contract.View, View.OnClickListener {

    private Presenter presenter = new Presenter(this);

    private Button buttonLogin;
    private EditText editTextLogin;
    private EditText editTextPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonLogin = findViewById(R.id.btnLogar);
        editTextLogin = findViewById(R.id.edtLogin);
        editTextPassword = findViewById(R.id.edtSenha);

        buttonLogin.setOnClickListener(this);
    }

    @Override
    public void login() {
        hideKeyboard();

        String login = editTextLogin.getText().toString();
        String password = editTextPassword.getText().toString();

        presenter.login(new Login(login, password));
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogar:
                login();
                break;
        }
    }

    @Override
    public void onLoggedIn() {
        Intent intent = new Intent(this, List_Places_Activity.class);
        startActivity(intent);
    }
}