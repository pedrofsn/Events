package br.com.pedrofsn.jobs.jobs.data.repository

import br.com.pedrofsn.jobs.jobs.data.model.JobItem

/*
    CREATED BY @PEDROFSN IN 24/10/20 12:05
*/

class RepositoryImpl : Repository {

    override fun getJobs(): List<JobItem> {
        val items = arrayListOf<JobItem>()
        for (i in 1..10) {
            val element = JobItem(
                id = 0,
                title = "Job #$i",
                date = "06/11/1992",
                place = "Campus Samambaia",
                description = "This is a cool Job!"
            )

            items.add(element)
        }
        return items
    }

}