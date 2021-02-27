package br.com.pedrofsn.jobs.jobs.data.repository

import br.com.pedrofsn.jobs.jobs.data.model.JobItem
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest

/*
    CREATED BY @PEDROFSN IN 24/10/20 12:04
*/

interface Repository {

    suspend fun getJobs(callback : CallbackNetworkRequest): List<JobItem>
}