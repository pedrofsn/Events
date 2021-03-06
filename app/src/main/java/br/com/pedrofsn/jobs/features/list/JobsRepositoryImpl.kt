package br.com.pedrofsn.jobs.features.list

import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest

class JobsRepositoryImpl(private val interactor: Interactor) : JobsRepository {

    override suspend fun getJobs(callback: CallbackNetworkRequest, page: Int) = with(interactor) {
        callback.receiveList(page)
    }
}