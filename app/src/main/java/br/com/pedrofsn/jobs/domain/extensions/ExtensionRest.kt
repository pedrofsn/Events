package br.com.pedrofsn.jobs.domain.extensions

import br.com.pedrofsn.jobs.domain.network.data.ErrorHandled
import br.com.pedrofsn.jobs.domain.network.data.PayloadError
import extract

fun PayloadError.toModel(networkError: Int) = ErrorHandled(
    message = extract safe msg,
    actionAPI = extract safe acao,
    statusCode = networkError,
    id = extract safe id
)