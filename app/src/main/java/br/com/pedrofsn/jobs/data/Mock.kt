package br.com.pedrofsn.jobs.data

import br.com.pedrofsn.jobs.data.model.JobItem

object Mock {

    val jobs = listOf(
        JobItem(
            id = 2,
            title = "Backend Developer",
            place = "São Paulo",
            date = "27/02/2021",
            description = "Trampo como dev backend"
        ),
        JobItem(
            id = 3,
            title = "Backend Developer",
            place = "Belo Horizonte",
            date = "26/02/2021",
            description = "Trampo como dev backend"
        ),
        JobItem(
            id = 4,
            title = "Backend Developer",
            place = "Manaus",
            date = "25/02/2021",
            description = "Trampo como dev backend"
        ),
        JobItem(
            id = 5,
            title = "Android Developer",
            place = "Goiânia",
            date = "06/11/1992",
            description = "Oi eu sou o Goku"
        )
    )
}