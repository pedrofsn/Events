package br.com.pedrofsn.network

interface Payload<T> {
    fun toModel(): T
}