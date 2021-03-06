package br.com.pedrofsn.jobs.domain.extensions

import br.com.pedrofsn.jobs.data.payload.PayloadError
import br.com.redcode.easyreftrofit.library.model.ErrorHandled
import extract

fun PayloadError.toModel(networkError: Int) = ErrorHandled(
    message = extract safe msg,
    actionAPI = extract safe acao,
    networkError = networkError,
    id = extract safe id
)