package br.com.pedrofsn.jobs.network.data.payload

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
) : Payload<String> {
    override fun toModel() = toString()
}

