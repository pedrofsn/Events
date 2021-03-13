package br.com.pedrofsn.jobs.domain.network

import br.com.pedrofsn.network.NetworkAndErrorHandler
import br.com.pedrofsn.network.NetworkFeedback
import br.com.pedrofsn.network.Payload

interface NetworkLayer<APIRetrofit> {

    val api: APIRetrofit
    val networkFeedback: NetworkFeedback

    suspend fun <TypePayload : Payload<TypeModel>, TypeModel> request(request: suspend APIRetrofit.() -> TypePayload?): TypeModel? {
        return try {
            request.invoke(api)
                ?.toModel()
                ?.also { networkFeedback.triggerSuccess() }
        } catch (exception: Exception) {
            handleError(exception)
            null
        }
    }

    private fun handleError(exception: Exception) {
        val networkAndErrorHandler = NetworkAndErrorHandler(networkFeedback)
        networkAndErrorHandler.handle(exception)
    }
}