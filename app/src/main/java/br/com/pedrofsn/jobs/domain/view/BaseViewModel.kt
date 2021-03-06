package br.com.pedrofsn.jobs.domain.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.pedrofsn.jobs.domain.network.NetworkError
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import br.com.redcode.easyreftrofit.library.model.ErrorHandled
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class BaseViewModel : ViewModel(), CallbackNetworkRequest, CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.IO
    val eventNetworkError = MutableLiveData<NetworkError>()

    override fun onNetworkError() {
        eventNetworkError.postValue(NetworkError.GenericError)
    }

    override fun onNetworkHttpError(errorHandled: ErrorHandled) {
        eventNetworkError.postValue(NetworkError.HttpError(errorHandled))
    }

    override fun onNetworkTimeout() {
        eventNetworkError.postValue(NetworkError.Timeout)
    }

    override fun onNetworkUnknownError(message: String) {
        eventNetworkError.postValue(NetworkError.UnknownError(message))
    }
}