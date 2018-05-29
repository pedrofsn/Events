package br.com.ufg.www.events.domain;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;

public abstract class BaseActivity extends Activity implements UIFeedback {

    @Override
    public void showMessage(@NonNull String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show();
    }

}
