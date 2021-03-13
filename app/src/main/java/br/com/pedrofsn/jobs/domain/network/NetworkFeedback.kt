package br.com.pedrofsn.jobs.domain.network

import androidx.lifecycle.MutableLiveData
import br.com.pedrofsn.jobs.domain.network.data.ErrorHandled
import br.com.pedrofsn.jobs.domain.network.data.NetworkErrorType

class NetworkFeedback : CallbackNetworkRequest {

    val eventNetworkError = MutableLiveData<NetworkErrorType>()

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

    private fun NetworkErrorType.notify() = eventNetworkError.postValue(this)
}