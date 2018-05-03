package br.com.ufg.www.events.mvp.login;

import br.com.ufg.www.events.model.Login;

public class Interactor implements Contract.Interactor {

    private Contract.Presenter presenter;

    public Interactor(Contract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void login(Login login) {
        // Disparar requisição
    }

}
