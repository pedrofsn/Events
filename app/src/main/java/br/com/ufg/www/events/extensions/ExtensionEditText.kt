package br.com.ufg.www.events.extensions

import android.content.Context
import android.text.method.PasswordTransformationMethod
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

/**
 * Created by pedrofsn on 17/10/2017.
 */

fun EditText.getString(): String {
    hideKeyboard()
    return text.toString().trim()
}

fun EditText.changeVisibilityPassword(): Boolean {
    return if (transformationMethod == null) {
        transformationMethod = PasswordTransformationMethod()
        true
    } else {
        transformationMethod = null
        false
    }
}

fun EditText.hideKeyboard() {
    val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}