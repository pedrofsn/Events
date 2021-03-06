package br.com.pedrofsn.jobs.domain.network

import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import br.com.redcode.easyreftrofit.library.Payload
import retrofit2.HttpException

interface NetworkLayer<APIRetrofit> {

    val api: APIRetrofit
    val networkFeedback: NetworkFeedback

    suspend fun <TypePayload : Payload<TypeModel>, TypeModel> request(request: suspend APIRetrofit.() -> TypePayload?): TypeModel? {
        return try {
            request.invoke(api)?.toModel()
        } catch (exception: Exception) {
            handleError(
                exception = exception,
                callbackNetworkRequest = networkFeedback,
            )
            null
        }
    }

    private fun handleError(exception: Exception, callbackNetworkRequest: CallbackNetworkRequest?) {
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