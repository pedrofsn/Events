package br.com.pedrofsn.network

import br.com.pedrofsn.network.data.ErrorHandled

interface CallbackNetworkRequest {

    fun onNetworkHttpError(errorHandled: ErrorHandled)
    fun onServerNotResponding()
    fun onNetworkTimeout()
    fun onUnknownHost()
    fun onNetworkUnknownError(message: String)
}