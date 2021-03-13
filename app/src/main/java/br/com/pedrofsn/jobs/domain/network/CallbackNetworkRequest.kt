package br.com.pedrofsn.jobs.domain.network

import br.com.pedrofsn.jobs.domain.network.data.ErrorHandled

interface CallbackNetworkRequest {

    fun onNetworkHttpError(errorHandled: ErrorHandled)
    fun onServerNotResponding()
    fun onNetworkTimeout()
    fun onUnknownHost()
    fun onNetworkUnknownError(message: String)
}