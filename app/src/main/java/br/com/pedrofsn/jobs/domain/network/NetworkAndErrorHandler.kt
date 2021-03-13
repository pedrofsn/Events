package br.com.pedrofsn.jobs.domain.network

import br.com.pedrofsn.jobs.BuildConfig
import br.com.pedrofsn.jobs.domain.extensions.toLogcat
import br.com.pedrofsn.jobs.domain.extensions.toModel
import br.com.pedrofsn.jobs.domain.network.data.ErrorHandled
import br.com.pedrofsn.jobs.domain.network.data.PayloadError
import com.squareup.moshi.Moshi
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import retrofit2.HttpException

private val MOSHI by lazy { Moshi.Builder().build() }

class NetworkAndErrorHandler(private val callbackNetworkRequest: CallbackNetworkRequest?) {

    fun handle(exception: Throwable) {
        when (exception) {
            is HttpException -> onNetworkHttpError(exception)
            is SocketTimeoutException -> callbackNetworkRequest?.onNetworkTimeout()
            is UnknownHostException -> callbackNetworkRequest?.onUnknownHost()
            is java.io.IOException -> callbackNetworkRequest?.onServerNotResponding()
            else -> {
                callbackNetworkRequest?.onNetworkUnknownError("Opss...")
                catchedException(exception)
            }
        }
    }

    private fun onNetworkHttpError(httpException: HttpException) {
        val networkError = httpException.code()
        val errorBody = httpException.response()?.errorBody()?.string()
        errorBody?.let { handleErrorJSONWithStatusCodeHTTP(it, networkError) }
    }

    private fun handleErrorJSONWithStatusCodeHTTP(errorBody: String, statusCode: Int) {
        try {
            val errorHandled = parseBodyError(errorBody, statusCode)
            callbackNetworkRequest?.onNetworkHttpError(errorHandled)
        } catch (e: Exception) {
            val errorHandled = ErrorHandled(message = e.toString(), statusCode = statusCode)
            callbackNetworkRequest?.onNetworkHttpError(errorHandled)
        }
    }

    private fun catchedException(exception: Throwable) {
        when {
            // TODO passar para o timber
            BuildConfig.DEBUG -> "Throwable message: ${exception.message}".toLogcat() // somente em debug
            else -> exception.printStackTrace()
        }
    }

    private fun parseBodyError(errorBodyAsString: String, statusCode: Int): ErrorHandled {
        val message = String.format("Erro %d do servidor", statusCode)
        return try {
            val adapter = MOSHI.adapter(PayloadError::class.java)
            val payloadError: PayloadError? = adapter.fromJson(errorBodyAsString)
            val modelError = payloadError?.toModel(statusCode)
            val result: ErrorHandled = modelError ?: ErrorHandled(message, statusCode)
            result
        } catch (e: Exception) {
            val error = PayloadError(msg = message, msg_dev = e.message)
            val errorHandled: ErrorHandled = error.toModel(statusCode)
            "Error in method 'parseBodyError' from class 'NetworkAndErrorHandler.kt': ${e.message}".toLogcat()
            errorHandled
        }
    }
}