package br.com.pedrofsn.jobs.features.list

import br.com.pedrofsn.jobs.data.API
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest

class RemoteDataSourceImpl(override val api: API) : RemoteDataSource {

    override suspend fun CallbackNetworkRequest.receiveList(page: Int) = request {
        receiveList(page)
    }
}