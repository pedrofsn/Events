package br.com.pedrofsn.jobs.domain.extensions

import androidx.lifecycle.Observer

inline fun <T> observer(crossinline code: (result: T) -> Unit): Observer<T> = Observer { obj: T? ->
    obj?.let { code(it) }
}