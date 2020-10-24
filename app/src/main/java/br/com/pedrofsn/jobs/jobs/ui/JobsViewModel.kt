package br.com.pedrofsn.jobs.jobs.ui

import androidx.lifecycle.ViewModel
import br.com.pedrofsn.jobs.jobs.data.repository.Repository

/*
    CREATED BY @PEDROFSN IN 24/10/20 15:39
*/

class JobsViewModel(private val repository: Repository) : ViewModel() {

    fun getJobs() = repository.getJobs()


}