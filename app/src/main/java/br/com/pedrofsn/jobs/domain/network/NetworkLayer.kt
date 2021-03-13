package br.com.pedrofsn.jobs.domain.network

interface NetworkLayer<APIRetrofit> {

    val api: APIRetrofit
    val networkFeedback: NetworkFeedback

    suspend fun <TypePayload : Payload<TypeModel>, TypeModel> request(request: suspend APIRetrofit.() -> TypePayload?): TypeModel? {
        return try {
            request.invoke(api)?.toModel()
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