package br.com.pedrofsn.jobs.domain

import androidx.lifecycle.ViewModel
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import br.com.redcode.easyreftrofit.library.model.ErrorHandled
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class BaseViewModel : ViewModel(), CallbackNetworkRequest, CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.IO

    override fun onNetworkError() {
        println("onNetworkError")
    }

    override fun onNetworkHttpError(errorHandled: ErrorHandled) {
        println("onNetworkHttpError $errorHandled")
    }

    override fun onNetworkTimeout() {
        println("onNetworkTimeout")
    }

    override fun onNetworkUnknownError(message: String) {
        println("onNetworkUnknownError $message")
    }
}