package br.com.pedrofsn.jobs.features.list

import br.com.pedrofsn.jobs.data.API
import br.com.pedrofsn.jobs.data.model.JobItems
import br.com.pedrofsn.jobs.domain.network.NetworkLayer

interface RemoteDataSource : NetworkLayer<API> {
    suspend fun receiveList(page: Int): JobItems?
}