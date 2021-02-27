package br.com.pedrofsn.jobs.network

import br.com.pedrofsn.jobs.jobs.data.payload.ResponseList
import br.com.pedrofsn.jobs.network.infra.Interactor

/*
    CREATED BY @PEDROFSN
*/

class InteractorImpl(private val api: API) : Interactor {

    override suspend fun receiveList(): ResponseList? = api.receiveList()
}