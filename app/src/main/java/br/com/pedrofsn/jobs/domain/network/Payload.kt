package br.com.pedrofsn.jobs.domain.network

interface Payload<T> {
    fun toModel(): T
}