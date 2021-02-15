package br.com.pedrofsn.jobs.jobs.data.payload

import br.com.pedrofsn.jobs.jobs.data.model.JobItem
import br.com.pedrofsn.jobs.network.data.payload.Pageable
import br.com.pedrofsn.jobs.network.data.payload.Sort
import br.com.redcode.easyreftrofit.library.Payload

data class ResponseList(
    val content: List<Any>,
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
    override fun toModel() = listOf(JobItem(id = 0, title = "AAA", place = "BBB", date = "CCCC", description = "DDD"))
}

