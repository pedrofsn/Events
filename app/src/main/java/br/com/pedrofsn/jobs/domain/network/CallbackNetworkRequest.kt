package br.com.pedrofsn.jobs.domain.network

interface CallbackNetworkRequest {

    fun onNetworkHttpError(errorHandled: ErrorHandled)
    fun onServerNotResponding()
    fun onNetworkTimeout()
    fun onUnknownHost()
    fun onNetworkUnknownError(message: String)
}