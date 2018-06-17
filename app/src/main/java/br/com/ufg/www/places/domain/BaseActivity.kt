package br.com.ufg.www.places.domain

import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View

abstract class BaseActivity : AppCompatActivity(), UIFeedback {

    override fun showMessage(message: String) = Snackbar.make(findViewById<View>(android.R.id.content), message, Snackbar.LENGTH_SHORT).show()

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (android.R.id.home == item?.itemId) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}