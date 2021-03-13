package br.com.pedrofsn.jobs.domain.network



sealed class NetworkErrorType {

    object Timeout : NetworkErrorType()
    object UnknownHost : NetworkErrorType()
    object ServerNotResponding : NetworkErrorType()
    data class UnknownError(val message: String) : NetworkErrorType()
    data class HttpError(val errorHandled: ErrorHandled) : NetworkErrorType()
}