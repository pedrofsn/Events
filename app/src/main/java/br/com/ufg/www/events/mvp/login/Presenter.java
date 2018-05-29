package br.com.ufg.www.events.mvp.login;

import br.com.ufg.www.events.model.Login;

public class Presenter implements Contract.Presenter {

    private Contract.View view;
    private Interactor interactor;

    public Presenter(Contract.View view) {
        this.view = view;
//        this.interactor = new Interactor();
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
