package br.com.pedrofsn.jobs.jobs.data.repository

import br.com.pedrofsn.jobs.jobs.data.model.JobItem
import br.com.pedrofsn.jobs.network.Interactor
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest

/*
    CREATED BY @PEDROFSN IN 24/10/20 12:05
*/

class RepositoryImpl(override val callbackNetworkRequest: CallbackNetworkRequest?) : Repository {

    private val interactor = Interactor(callbackNetworkRequest)

    override suspend fun getJobs(): List<JobItem> = interactor.receiveList() ?: emptyList()
}