package br.com.pedrofsn.jobs.features.list

import br.com.pedrofsn.jobs.data.payload.ResponseList
import br.com.pedrofsn.jobs.network.API

class InteractorImpl(private val api: API) : Interactor {

    override suspend fun receiveList(): ResponseList? = api.receiveList()
}