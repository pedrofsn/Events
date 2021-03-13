package br.com.pedrofsn.network

import br.com.pedrofsn.network.data.ErrorHandled
import br.com.pedrofsn.network.data.PayloadError
import extract

fun PayloadError.toModel(networkError: Int) = ErrorHandled(
    message = extract safe msg,
    actionAPI = extract safe acao,
    statusCode = networkError,
    id = extract safe id
)