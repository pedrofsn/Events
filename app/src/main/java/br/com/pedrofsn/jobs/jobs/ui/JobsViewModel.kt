package br.com.pedrofsn.jobs.jobs.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.pedrofsn.jobs.jobs.data.model.JobItem
import br.com.pedrofsn.jobs.jobs.data.repository.Repository

/*
    CREATED BY @PEDROFSN IN 24/10/20 15:39
*/

class JobsViewModel(private val repository: Repository) : ViewModel() {

    private val _jobs = MutableLiveData<List<JobItem>>()
    val jobs: LiveData<List<JobItem>> = _jobs

    fun initialize() {
        val list = repository.getJobs()
        _jobs.postValue(list)
    }

}