package br.com.pedrofsn.jobs.features.list

import br.com.pedrofsn.jobs.data.API
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest

class InteractorImpl(override val api: API) : Interactor {

    override suspend fun CallbackNetworkRequest.receiveList(page: Int) = request {
        api.receiveList(page)
    }
}