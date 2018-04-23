package br.com.ufg.www.events.mvp.login;

import br.com.ufg.www.events.model.Login;

public class Presenter implements Contract.Presenter {

    private Contract.View view;

    public Presenter(Contract.View view) {
        this.view = view;
    }

    @Override
    public void login(Login login) {

    }

}
