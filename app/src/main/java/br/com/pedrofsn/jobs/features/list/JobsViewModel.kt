package br.com.pedrofsn.jobs.features.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.pedrofsn.jobs.domain.BaseViewModel
import br.com.pedrofsn.jobs.data.model.JobItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JobsViewModel(private val repository: JobsRepository) : BaseViewModel() {

    private val _jobs = MutableLiveData<List<JobItem>>()
    val jobs: LiveData<List<JobItem>> = _jobs

    fun initialize() {
        launch(Dispatchers.IO) {
            val list = repository.getJobs(this@JobsViewModel)
            _jobs.postValue(list)
        }
    }
}