package br.com.pedrofsn.jobs.domain.network

import br.com.pedrofsn.jobs.data.API

object APIConnection : CustomAPIConnection<API>() {
    override val classz = API::class.java
}