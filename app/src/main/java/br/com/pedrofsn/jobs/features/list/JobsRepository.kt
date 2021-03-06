package br.com.pedrofsn.jobs.features.list

import br.com.pedrofsn.jobs.data.model.JobItems

interface JobsRepository {

    suspend fun getJobs(page: Int): JobItems?
}