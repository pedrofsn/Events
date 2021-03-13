package br.com.pedrofsn.jobs.domain.extensions

import br.com.pedrofsn.network.Payload

fun <T> List<Payload<T>>?.toModel(): List<T>? = this?.map { obj -> obj.toModel() }

fun String?.toLogcat() = this?.let { println(it) }