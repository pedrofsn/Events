package br.com.pedrofsn.jobs.jobs.data.repository

import br.com.pedrofsn.jobs.jobs.data.model.JobItem
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest

interface Repository {

    suspend fun getJobs(callback : CallbackNetworkRequest): List<JobItem>
}