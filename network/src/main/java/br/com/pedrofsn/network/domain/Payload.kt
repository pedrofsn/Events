package br.com.pedrofsn.network.domain

interface Payload<T> {
    fun toModel(): T
}