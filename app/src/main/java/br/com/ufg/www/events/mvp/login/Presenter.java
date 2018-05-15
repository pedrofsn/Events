package br.com.ufg.www.events.mvp.login;

import android.content.Intent;

import br.com.ufg.www.events.model.Login;
import br.com.ufg.www.events.mvp.maps.List_Places_Activity;

public class Presenter implements Contract.Presenter {

    private Contract.View view;
    private Interactor interactor;

    public Presenter(Contract.View view) {
        this.view = view;
        this.interactor = new Interactor();
    }

    @Override
    public void login(Login login) {
        attempLogin(login.getLogin(), login.getPassword());
    }

    public void attempLogin(String email, String senha){
        boolean isValid = interactor.validadtedCredentials(email);
        if (isValid){
            view.onLoggedIn();
        } else {
            view.loginFailed();
        }
    }


}
