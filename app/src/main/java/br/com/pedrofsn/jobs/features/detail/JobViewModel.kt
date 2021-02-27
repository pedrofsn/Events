package br.com.pedrofsn.jobs.features.detail

import androidx.lifecycle.MutableLiveData
import br.com.pedrofsn.jobs.domain.BaseViewModel
import br.com.pedrofsn.jobs.data.model.JobItem

class JobViewModel : BaseViewModel() {

    val data = MutableLiveData<JobItem>()

}