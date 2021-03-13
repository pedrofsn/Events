package br.com.pedrofsn.network.feature

import br.com.pedrofsn.network.domain.CallbackNetworkRequest
import br.com.pedrofsn.network.domain.NetworkErrorType
import br.com.pedrofsn.network.model.ErrorHandled
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