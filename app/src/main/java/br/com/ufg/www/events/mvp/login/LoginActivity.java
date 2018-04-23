package br.com.ufg.www.events.mvp.login;

import android.os.Bundle;
import android.support.annotation.Nullable;

import br.com.ufg.www.events.domain.BaseActivity;
import br.com.ufg.www.events.model.Login;

public class LoginActivity extends BaseActivity implements Contract.View {

    private Presenter presenter = new Presenter(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        TODO 1 - Implementar o layout/XML e setar no setContentView
//        TODO 2 - O onClick no botão de login deve chamar o método abaixo ('login()')
    }

    @Override
    public void login() {
        String loginString = "";
        String passwordString = "";
//        TODO 3 Obter o login e senha inputados na UI e fazer as validações necessárias

//        TODO 4 Se tudo estiver ok...
        Login login = new Login(loginString, passwordString);
        presenter.login(login);

    }

    @Override
    public void onLoggedIn() {

    }
}
