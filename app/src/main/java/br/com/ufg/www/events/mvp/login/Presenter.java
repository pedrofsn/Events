package br.com.ufg.www.events.mvp.login;

import android.content.Intent;

import br.com.ufg.www.events.model.Login;
import br.com.ufg.www.events.mvp.maps.List_Places_Activity;

public class Presenter implements Contract.Presenter {

    private Contract.View view;

    public Presenter(Contract.View view) {
        this.view = view;
    }

    @Override
    public void login(Login login) {
        //
    }




}
