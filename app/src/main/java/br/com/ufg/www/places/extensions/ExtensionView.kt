package br.com.ufg.www.places.extensions

import android.view.View

/**
 * Created by pedrofsn on 01/12/17.
 */
fun View.showOrHide(showOrHide: Boolean) {
    if (showOrHide) visible() else gone()
}

fun View.invisible() {
    changeVisibility(this, View.INVISIBLE)
}

fun View.gone() {
    changeVisibility(this, View.GONE)
}

fun View.visible() {
    changeVisibility(this, View.VISIBLE)
}

private fun changeVisibility(view: View, visibility: Int) {
    view.visibility = visibility
}