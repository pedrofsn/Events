package br.com.pedrofsn.jobs.domain.network

import androidx.lifecycle.MutableLiveData


class NetworkFeedback : CallbackNetworkRequest {

    val eventNetworkError = MutableLiveData<NetworkErrorType>()

    override fun onServerNotResponding() {
        eventNetworkError.postValue(NetworkErrorType.ServerNotResponding)
    }

    override fun onUnknownHost() {
        eventNetworkError.postValue(NetworkErrorType.UnknownHost)
    }

    override fun onNetworkHttpError(errorHandled: ErrorHandled) {
        eventNetworkError.postValue(NetworkErrorType.HttpError(errorHandled))
    }

    override fun onNetworkTimeout() {
        eventNetworkError.postValue(NetworkErrorType.Timeout)
    }

    override fun onNetworkUnknownError(message: String) {
        eventNetworkError.postValue(NetworkErrorType.UnknownError(message))
    }
}