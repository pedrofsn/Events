package br.com.pedrofsn.jobs.jobs.data.payload

import br.com.pedrofsn.jobs.jobs.data.model.JobItem
import br.com.redcode.easyreftrofit.library.Payload

data class ResponseList(
    val content: List<Content>?,
    val empty: Boolean,
    val first: Boolean,
    val last: Boolean,
    val number: Int,
    val numberOfElements: Int,
    val pageable: Pageable,
    val size: Int,
    val sort: Sort,
    val totalElements: Int,
    val totalPages: Int
) : Payload<List<JobItem>> {
    override fun toModel() = content?.map { it.toModel() } ?: emptyList()
}