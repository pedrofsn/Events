package br.com.pedrofsn.jobs.network

import br.com.pedrofsn.jobs.jobs.data.payload.ResponseList
import br.com.pedrofsn.jobs.network.infra.Interactor

class InteractorImpl(private val api: API) : Interactor {

    override suspend fun receiveList(): ResponseList? = api.receiveList()
}