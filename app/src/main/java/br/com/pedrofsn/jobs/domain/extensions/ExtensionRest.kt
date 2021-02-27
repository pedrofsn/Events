package br.com.pedrofsn.jobs.domain.extensions

import br.com.pedrofsn.jobs.data.payload.PayloadError
import br.com.pedrofsn.jobs.network.NetworkAndErrorHandler
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import br.com.redcode.easyreftrofit.library.Payload
import br.com.redcode.easyreftrofit.library.model.ErrorHandled
import extract
import retrofit2.HttpException
import java.net.UnknownHostException

fun PayloadError.toModel(networkError: Int) = ErrorHandled(
    message = extract safe msg,
    actionAPI = extract safe acao,
    networkError = networkError,
    id = extract safe id
)

suspend fun <TypePayload : Payload<TypeModel>, TypeModel> TypePayload.doRequest(
    callbackNetworkRequest: CallbackNetworkRequest? = null,
    handleErrorManual: ((String?) -> Unit)? = null,
    handleFailureManual: ((Throwable) -> Unit)? = null
): TypeModel? {
    return try {
        val model = toModel()
        model
    } catch (e: Exception) {
        when (e) {
            is HttpException -> {
                val code = e.code()
                val errorBody = e.response()?.errorBody()?.string()

                when {
                    handleErrorManual == null && errorBody != null -> {
                        NetworkAndErrorHandler(
                            callbackNetworkRequest
                        ).handleErrorJSONWithStatusCodeHTTP(errorBody, code)
                    }

                    handleErrorManual != null -> handleErrorManual.invoke(errorBody)

                    handleFailureManual != null -> handleFailureManual.invoke(e)

                    else -> {
                        NetworkAndErrorHandler(callbackNetworkRequest).handle(e)
                        e.printStackTrace()
                    }
                }
            }
            is UnknownHostException -> throw e
            else -> e.printStackTrace()
        }

        return null
    }
}