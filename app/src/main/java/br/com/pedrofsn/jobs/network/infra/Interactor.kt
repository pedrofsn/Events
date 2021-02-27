package br.com.pedrofsn.jobs.network.infra

import br.com.pedrofsn.jobs.jobs.data.payload.ResponseList

interface Interactor {
    suspend fun receiveList(): ResponseList?
}