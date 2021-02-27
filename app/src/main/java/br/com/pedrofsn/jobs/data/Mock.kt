package br.com.pedrofsn.jobs.data

import br.com.pedrofsn.jobs.data.model.JobItem

object Mock {

    val jobs = listOf(
        JobItem(
            id = 0,
            title = "Android Developer",
            place = "Goiânia",
            date = "06/11/992",
            description = "Oi eu sou o Goku"
        ),
        JobItem(
            id = 0,
            title = "Backend Developer",
            place = "São Paulo",
            date = "07/11/2021",
            description = "Trampo como dev backend"
        )
    )
}