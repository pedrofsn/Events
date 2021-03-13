package br.com.pedrofsn.jobs.domain.network.data

import br.com.pedrofsn.jobs.domain.Constants.EMPTY_STRING

data class ErrorHandled(
    val message: String,
    val actionAPI: Int = -1,
    val statusCode: Int = -1,
    val id: String = EMPTY_STRING
)