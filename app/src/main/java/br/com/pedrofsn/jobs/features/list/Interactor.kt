package br.com.pedrofsn.jobs.features.list

import br.com.pedrofsn.jobs.data.payload.ResponseList

interface Interactor {
    suspend fun receiveList(): ResponseList?
}