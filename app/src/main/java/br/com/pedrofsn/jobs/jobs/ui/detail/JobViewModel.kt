package br.com.pedrofsn.jobs.jobs.ui.detail

import androidx.lifecycle.MutableLiveData
import br.com.pedrofsn.jobs.domain.BaseViewModel
import br.com.pedrofsn.jobs.jobs.data.model.JobItem

class JobViewModel : BaseViewModel() {

    val data = MutableLiveData<JobItem>()

}