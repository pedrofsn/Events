package br.com.ufg.www.events.domain;

import android.app.Activity;
import android.support.design.widget.Snackbar;

public abstract class BaseActivity extends Activity implements UIFeedback {

    @Override
    public void showMessage(String message) {
        Snackbar.make(getWindow().getDecorView().getRootView(), message, Snackbar.LENGTH_SHORT).show();
    }

}
