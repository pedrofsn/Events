package br.com.pedrofsn.jobs.domain.network

import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import br.com.redcode.easyreftrofit.library.Payload
import retrofit2.HttpException

interface NetworkLayer<APIRetrofit> {

    val api: APIRetrofit

    suspend fun <TypePayload : Payload<TypeModel>, TypeModel> CallbackNetworkRequest.request(request: suspend APIRetrofit.() -> TypePayload?): TypeModel? {
        return try {
            val service = api
            val result = request.invoke(service)
            result?.toModel()
        } catch (exception: Exception) {
            handleError(
                exception = exception,
                callbackNetworkRequest = this,
                handleErrorManual = null, // TODO [PEDROFSN]
                handleFailureManual = null
            )
            null
        }
    }

    private fun handleError(
        exception: Exception,
        callbackNetworkRequest: CallbackNetworkRequest?,
        handleErrorManual: ((String?) -> Unit)?,
        handleFailureManual: ((Throwable) -> Unit)?
    ) {
        val code = (exception as? HttpException)?.code()
        val errorBody = (exception as? HttpException)?.response()?.errorBody()?.string()
        val isHttpError = exception is HttpException
            && handleErrorManual == null
            && code != null
            && errorBody != null

        when {
            isHttpError -> {
                val handler = NetworkAndErrorHandler(callbackNetworkRequest)
                handler.handleErrorJSONWithStatusCodeHTTP(errorBody!!, code!!)
            }

            handleErrorManual != null -> handleErrorManual.invoke(errorBody)
            handleFailureManual != null -> handleFailureManual.invoke(exception)

            else -> {
                exception.printStackTrace()
                NetworkAndErrorHandler(callbackNetworkRequest = callbackNetworkRequest).apply {
                    handle(exception)
                }
            }
        }
    }
}