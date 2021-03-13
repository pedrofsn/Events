package br.com.pedrofsn.jobs.features.list

import br.com.pedrofsn.jobs.data.API
import br.com.pedrofsn.jobs.data.model.JobItems
import br.com.pedrofsn.jobs.data.payload.Content
import br.com.pedrofsn.jobs.data.payload.Pageable
import br.com.pedrofsn.jobs.data.payload.ResponseList
import br.com.pedrofsn.jobs.data.payload.Sort
import br.com.pedrofsn.network.feature.NetworkFeedback

class MockedRemoteDataSourceImpl(
    override val api: API,
    override val networkFeedback: NetworkFeedback
) : RemoteDataSource {

    override suspend fun receiveList(page: Int): JobItems? {
        val mock = ResponseList(
            content = listOf(
                Content(
                    id = 2,
                    titulo = "Backend Developer",
                    local = "São Paulo",
                    data = "27/02/2021",
                    descricao = "Trampo como dev backend"
                ),
                Content(
                    id = 3,
                    titulo = "Backend Developer",
                    local = "Belo Horizonte",
                    data = "26/02/2021",
                    descricao = "Trampo como dev backend"
                ),
                Content(
                    id = 4,
                    titulo = "Backend Developer",
                    local = "Manaus",
                    data = "25/02/2021",
                    descricao = "Trampo como dev backend"
                ),
                Content(
                    id = 5,
                    titulo = "Android Developer",
                    local = "Goiânia",
                    data = "06/11/1992",
                    descricao = "Oi eu sou o Goku"
                )
            ),
            empty = false,
            first = false,
            last = false,
            number = 0,
            numberOfElements = 0,
            pageable = Pageable(
                offset = 0,
                pageNumber = 0,
                pageSize = 0,
                paged = false,
                sort = Sort(
                    empty = false,
                    sorted = false,
                    unsorted = false
                ),
                unpaged = false
            ),
            size = 0,
            sort = Sort(
                empty = false,
                sorted = false,
                unsorted = false
            ),
            totalElements = 0,
            totalPages = 0
        )
        return mock.toModel()
    }
}