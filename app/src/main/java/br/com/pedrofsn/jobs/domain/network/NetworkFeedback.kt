package br.com.pedrofsn.jobs.domain.network

import androidx.lifecycle.MutableLiveData
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import br.com.redcode.easyreftrofit.library.model.ErrorHandled

class NetworkFeedback : CallbackNetworkRequest {

    val eventNetworkError = MutableLiveData<NetworkErrorType>()

    override fun onNetworkError() {
        eventNetworkError.postValue(NetworkErrorType.GenericError)
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