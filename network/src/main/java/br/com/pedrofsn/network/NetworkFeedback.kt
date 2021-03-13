package br.com.pedrofsn.network

import br.com.pedrofsn.network.data.ErrorHandled
import br.com.pedrofsn.network.data.NetworkErrorType
import kotlinx.coroutines.flow.MutableStateFlow

class NetworkFeedback : CallbackNetworkRequest {

    val eventNetworkError = MutableStateFlow<NetworkErrorType>(NetworkErrorType.Nothing)

    override fun onServerNotResponding() {
        NetworkErrorType.ServerNotResponding.notify()
    }

    override fun onUnknownHost() {
        NetworkErrorType.UnknownHost.notify()
    }

    override fun onNetworkHttpError(errorHandled: ErrorHandled) {
        NetworkErrorType.HttpError(errorHandled).notify()
    }

    override fun onNetworkTimeout() {
        NetworkErrorType.Timeout.notify()
    }

    override fun onNetworkUnknownError(message: String) {
        NetworkErrorType.UnknownError(message).notify()
    }

    private fun NetworkErrorType.notify() {
        eventNetworkError.value = this
    }
}