package br.com.pedrofsn.jobs.features.list

import br.com.pedrofsn.jobs.data.API
import br.com.pedrofsn.jobs.domain.network.NetworkFeedback

class RemoteDataSourceImpl(
    override val api: API,
    override val networkFeedback: NetworkFeedback
) : RemoteDataSource {

    override suspend fun receiveList(page: Int) = request { receiveList(page) }
}