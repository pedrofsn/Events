package br.com.pedrofsn.network.model

import br.com.pedrofsn.network.domain.Constants.EMPTY_STRING

data class ErrorHandled(
    val message: String,
    val actionAPI: Int = -1,
    val statusCode: Int = -1,
    val id: String = EMPTY_STRING
)