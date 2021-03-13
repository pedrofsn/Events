package br.com.pedrofsn.jobs.features.list

import androidx.lifecycle.MutableLiveData
import br.com.pedrofsn.jobs.domain.pagination.Pagination
import br.com.pedrofsn.jobs.domain.view.BaseViewModel
import br.com.pedrofsn.network.NetworkFeedback

class JobsViewModel(
    private val repository: JobsRepository,
    override val networkFeedback: NetworkFeedback
) : BaseViewModel() {

    val loading = MutableLiveData(false)
    val error = MutableLiveData(false)

    val pagination by lazy {
        return@lazy Pagination(
            scope = this,
            onPreExecute = { loading.postValue(true) },
            doInBackground = { _, page -> repository.getJobs(page) },
            onPostExecute = { list, _ ->
                println("lista com ${list.size}")
            },
            onSucces = {
                loading.postValue(false)
                error.postValue(false)
            },
            onError = {
                loading.postValue(false)
                error.postValue(true)
            }
        )
    }
}

