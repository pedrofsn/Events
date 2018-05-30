package br.com.ufg.www.events.extensions

import android.view.View
import android.widget.TextView

/**
 * Created by pedrofsn on 30/11/2017.
 */

fun TextView.getString() = text.toString().trim()

fun TextView.setTextOrHide(string: String?, viewToHide: View? = null) {
    if (string == null || string.isNullOrBlank()) {
        gone()
        viewToHide?.gone()
    } else {
        visible()
        viewToHide?.visible()
        text = string
    }
}