package br.com.pedrofsn.jobs.features.list

import br.com.pedrofsn.jobs.data.API
import br.com.pedrofsn.jobs.data.model.JobItems
import br.com.pedrofsn.jobs.domain.network.NetworkLayer
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest

interface Interactor : NetworkLayer<API> {
    suspend fun CallbackNetworkRequest.receiveList(page: Int): JobItems?
}