package br.com.pedrofsn.network.data

sealed class NetworkErrorType {
    object Nothing : NetworkErrorType()
    object Timeout : NetworkErrorType()
    object UnknownHost : NetworkErrorType()
    object ServerNotResponding : NetworkErrorType()
    data class UnknownError(val message: String) : NetworkErrorType()
    data class HttpError(val errorHandled: ErrorHandled) : NetworkErrorType()
}