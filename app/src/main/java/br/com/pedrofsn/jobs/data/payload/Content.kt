package br.com.pedrofsn.jobs.data.payload

import br.com.pedrofsn.jobs.data.model.JobItem
import br.com.redcode.easyreftrofit.library.Payload
import extract

data class Content(
    val id: Long,
    val titulo: String,
    val local: String,
    val `data`: String,
    val descricao: String
) : Payload<JobItem> {
    override fun toModel() = JobItem(
        id = extract safe id,
        title = extract safe titulo,
        place = extract safe local,
        date = extract safe data,
        description = extract safe descricao
    )
}