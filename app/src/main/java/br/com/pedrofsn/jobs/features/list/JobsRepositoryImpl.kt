package br.com.pedrofsn.jobs.features.list

class JobsRepositoryImpl(private val remote: RemoteDataSource) : JobsRepository {

    override suspend fun getJobs(page: Int) = remote.receiveList(page)
}