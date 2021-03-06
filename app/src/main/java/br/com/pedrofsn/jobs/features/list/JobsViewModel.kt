package br.com.pedrofsn.jobs.features.list

import androidx.lifecycle.MutableLiveData
import br.com.pedrofsn.jobs.domain.network.NetworkFeedback
import br.com.pedrofsn.jobs.domain.pagination.Pagination
import br.com.pedrofsn.jobs.domain.view.BaseViewModel

class JobsViewModel(
    private val repository: JobsRepository,
    override val networkFeedback: NetworkFeedback
) : BaseViewModel() {

    val loading = MutableLiveData(false)

    val pagination by lazy {
        return@lazy Pagination(
            scope = this,
            onPreExecute = { loading.postValue(true) },
            filter = { null },
            doInBackground = { _, page -> repository.getJobs(page) },
            onPostExecute = { list, aa: Any? -> println("lista com ${list.size}") }, // TODO [pedrofsn] faz nada com isto?
            handleEmptyData = { show -> /*showEmptyView.set(show)*/ }
        )
    }
}

