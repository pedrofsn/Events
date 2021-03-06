package br.com.pedrofsn.jobs.domain.network

import br.com.redcode.easyreftrofit.library.model.ErrorHandled

sealed class NetworkErrorType {

    object GenericError : NetworkErrorType()
    data class HttpError(val errorHandled: ErrorHandled) : NetworkErrorType()
    object Timeout : NetworkErrorType()
    data class UnknownError(val message: String) : NetworkErrorType()
}