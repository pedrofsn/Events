package br.com.pedrofsn.jobs.features.list

import br.com.pedrofsn.jobs.data.model.JobItems
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest

interface JobsRepository {

    suspend fun getJobs(callback: CallbackNetworkRequest, page: Int): JobItems?
}