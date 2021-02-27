package br.com.pedrofsn.jobs.jobs.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.pedrofsn.jobs.domain.BaseViewModel
import br.com.pedrofsn.jobs.jobs.data.model.JobItem
import br.com.pedrofsn.jobs.jobs.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JobsViewModel(private val repository: Repository) : BaseViewModel() {

    private val _jobs = MutableLiveData<List<JobItem>>()
    val jobs: LiveData<List<JobItem>> = _jobs

    fun initialize() {
        launch(Dispatchers.IO) {
            val list = repository.getJobs(this@JobsViewModel)
            _jobs.postValue(list)
        }
    }
}