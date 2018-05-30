package br.com.ufg.www.events.domain

import android.app.Activity
import android.support.design.widget.Snackbar
import android.view.View

abstract class BaseActivity : Activity(), UIFeedback {

    override fun showMessage(message: String) = Snackbar.make(findViewById<View>(android.R.id.content), message, Snackbar.LENGTH_SHORT).show()

}