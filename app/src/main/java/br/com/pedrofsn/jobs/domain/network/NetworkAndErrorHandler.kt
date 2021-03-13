package br.com.pedrofsn.jobs.domain.network

import br.com.pedrofsn.jobs.BuildConfig
import br.com.pedrofsn.jobs.data.payload.PayloadError
import br.com.pedrofsn.jobs.domain.extensions.toLogcat
import br.com.pedrofsn.jobs.domain.extensions.toModel

import com.squareup.moshi.Moshi
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import retrofit2.HttpException

private val moshi by lazy { Moshi.Builder().build() }

class NetworkAndErrorHandler(private val callbackNetworkRequest: CallbackNetworkRequest?) {

    private val message by lazy { "Erro %d do servidor" }

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

    private fun parseBodyError(errorBodyAsString: String, networkError: Int): ErrorHandled {
        return try {
            val adapter = moshi.adapter(PayloadError::class.java)
            val payloadError: PayloadError? = adapter.fromJson(errorBodyAsString)
            val modelError = payloadError?.toModel(networkError)
            val result: ErrorHandled = modelError ?: ErrorHandled(
                message = message,
                statusCode = networkError
            )
            result
        } catch (e: Exception) {
            val message = String.format(message, networkError)
            val error = PayloadError(msg = message, msg_dev = e.message)
            val errorHandled: ErrorHandled = error.toModel(networkError)
            "Error in method 'parseBodyError' from class 'NetworkAndErrorHandler.kt': ${e.message}".toLogcat()
            errorHandled
        }
    }
}