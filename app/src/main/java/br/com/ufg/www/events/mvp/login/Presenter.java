package br.com.ufg.www.events.mvp.login;

import br.com.ufg.www.events.model.Login;

public class Presenter implements Contract.Presenter {

    private Contract.View view;
    private Interactor interactor;

    public Presenter(Contract.View view) {
        this.view = view;
        this.interactor = new Interactor();
    }

    @Override
    public void login(Login login) {
        Boolean result = interactor.login(login);
        if (result) {
            view.onLoggedIn();
        } else {
            view.loginFailed();
        }
    }
}
