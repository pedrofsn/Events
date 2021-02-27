package br.com.pedrofsn.jobs.features.list

import br.com.pedrofsn.jobs.data.Mock
import br.com.pedrofsn.jobs.data.model.JobItem
import br.com.pedrofsn.jobs.network.NetworkAndErrorHandler
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import br.com.redcode.easyreftrofit.library.Payload
import java.net.UnknownHostException
import retrofit2.HttpException

class JobsRepositoryImpl(private val interactor: Interactor) : JobsRepository {

    override suspend fun getJobs(callback: CallbackNetworkRequest): List<JobItem> {
        return Mock.jobs
        // TODO tratar erros de conexão
        // return try {
        //     interactor.receiveList()?.toModel() ?: emptyList()
        // } catch (e: Exception) {
        //     e.printStackTrace()
        //     emptyList()
        // }
    }

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
}