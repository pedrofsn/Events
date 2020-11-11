package br.com.pedrofsn.jobs.jobs.data.model

data class JobItem(
    val id: Long,
    val title: String,
    val place: String,
    val date: String,
    val description: String
)