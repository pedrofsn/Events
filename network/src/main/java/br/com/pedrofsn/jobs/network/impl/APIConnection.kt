package br.com.pedrofsn.jobs.network.impl

import br.com.pedrofsn.jobs.network.infra.CustomAPIConnection

object APIConnection : CustomAPIConnection<API>() {
    override val classz = API::class.java
}