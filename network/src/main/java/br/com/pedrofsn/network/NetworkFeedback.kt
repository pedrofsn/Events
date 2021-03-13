package br.com.pedrofsn.network

import br.com.pedrofsn.network.data.ErrorHandled
import br.com.pedrofsn.network.data.NetworkErrorType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NetworkFeedback : CallbackNetworkRequest {

    private val eventNetworkError = MutableStateFlow<NetworkErrorType>(NetworkErrorType.Nothing)
    val stateNetworkError = eventNetworkError.asStateFlow()

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

    fun triggerSuccess() = NetworkErrorType.Nothing.notify()

    private fun NetworkErrorType.notify() {
        eventNetworkError.value = this
    }
}