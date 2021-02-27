package br.com.pedrofsn.jobs.features.detail

import androidx.lifecycle.MutableLiveData
import br.com.pedrofsn.jobs.data.model.JobItem
import br.com.pedrofsn.jobs.domain.BaseViewModel

class JobViewModel : BaseViewModel() {

    val data = MutableLiveData<JobItem>()
}