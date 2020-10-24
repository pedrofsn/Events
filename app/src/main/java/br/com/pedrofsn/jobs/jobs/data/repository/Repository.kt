package br.com.pedrofsn.jobs.jobs.data.repository

import br.com.pedrofsn.jobs.jobs.data.model.JobItem

/*
    CREATED BY @PEDROFSN IN 24/10/20 12:04
*/

interface Repository {

    fun getJobs() : List<JobItem>

}