package br.com.pedrofsn.jobs.features.list

import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest

class JobsRepositoryImpl(private val dataSource: RemoteDataSource) : JobsRepository {

    override suspend fun getJobs(callback: CallbackNetworkRequest, page: Int) = with(dataSource) {
        callback.receiveList(page)
    }
}