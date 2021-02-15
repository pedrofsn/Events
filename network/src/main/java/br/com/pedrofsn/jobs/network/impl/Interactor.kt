package br.com.pedrofsn.jobs.network.impl

import br.com.pedrofsn.jobs.network.domain.api
import br.com.pedrofsn.jobs.network.domain.doRequest
import br.com.pedrofsn.jobs.network.infra.BaseInteractor
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest


/*
    CREATED BY @PEDROFSN
*/

class Interactor(override val callbackNetworkRequest: CallbackNetworkRequest?) : BaseInteractor {

    suspend fun receiveList() = api()
        .receiveList()
        .doRequest(callbackNetworkRequest)

}