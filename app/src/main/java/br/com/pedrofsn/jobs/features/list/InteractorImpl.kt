package br.com.pedrofsn.jobs.features.list

import br.com.pedrofsn.jobs.data.API
import br.com.pedrofsn.jobs.data.payload.ResponseList

class InteractorImpl(private val api: API) : Interactor {

    override suspend fun receiveList(): ResponseList? = api.receiveList()
}