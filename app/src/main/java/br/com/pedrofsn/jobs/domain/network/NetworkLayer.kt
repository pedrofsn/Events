package br.com.pedrofsn.jobs.domain.network

import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import br.com.redcode.easyreftrofit.library.Payload
import retrofit2.HttpException

interface NetworkLayer<APIRetrofit> {

    val api: APIRetrofit

    suspend fun <TypePayload : Payload<TypeModel>, TypeModel> CallbackNetworkRequest.request(request: suspend APIRetrofit.() -> TypePayload?): TypeModel? {
        return try {
            request.invoke(api)?.toModel()
        } catch (exception: Exception) {
            handleError(
                exception = exception,
                callbackNetworkRequest = this,
            )
            null
        }
    }

    private fun handleError(
        exception: Exception,
        callbackNetworkRequest: CallbackNetworkRequest?
    ) {
        val code = (exception as? HttpException)?.code()
        val errorBody = (exception as? HttpException)?.response()?.errorBody()?.string()
        val isHttpError = exception is HttpException
            && code != null
            && errorBody != null

        NetworkAndErrorHandler(callbackNetworkRequest).run {
            if (isHttpError) {
                handleErrorJSONWithStatusCodeHTTP(errorBody!!, code!!)
            } else {
                exception.printStackTrace()
                handle(exception)
            }
        }
    }
}