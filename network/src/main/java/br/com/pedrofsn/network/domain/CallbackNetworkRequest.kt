package br.com.pedrofsn.network.domain

import br.com.pedrofsn.network.model.ErrorHandled

interface CallbackNetworkRequest {

    fun onNetworkHttpError(errorHandled: ErrorHandled)
    fun onServerNotResponding()
    fun onNetworkTimeout()
    fun onUnknownHost()
    fun onNetworkUnknownError(message: String)
}