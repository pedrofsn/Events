package br.com.pedrofsn.jobs.features.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.pedrofsn.jobs.data.model.JobItem
import br.com.pedrofsn.jobs.domain.view.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JobsViewModel(private val repository: JobsRepository) : BaseViewModel() {

    private val _jobs = MutableLiveData<List<JobItem>>()
    val jobs: LiveData<List<JobItem>> = _jobs
    val loading = MutableLiveData(false)

    fun load() {
        loading.value = true
        launch(Dispatchers.IO) {
            val list = repository.getJobs(this@JobsViewModel)
            _jobs.postValue(list)
            loading.postValue(false)
        }
    }
}

