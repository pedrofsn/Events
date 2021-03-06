package br.com.pedrofsn.jobs.domain.network

import br.com.redcode.easyreftrofit.library.model.ErrorHandled

sealed class NetworkError {

    object GenericError : NetworkError()
    data class HttpError(val errorHandled: ErrorHandled) : NetworkError()
    object Timeout : NetworkError()
    data class UnknownError(val message: String) : NetworkError()
}