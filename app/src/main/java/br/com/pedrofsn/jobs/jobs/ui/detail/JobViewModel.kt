package br.com.pedrofsn.jobs.jobs.ui.detail

import androidx.lifecycle.MutableLiveData
import br.com.pedrofsn.jobs.domain.BaseViewModel
import br.com.pedrofsn.jobs.jobs.data.model.JobItem

/*
    CREATED BY @PEDROFSN IN 14/11/20 08:20
*/

class JobViewModel : BaseViewModel() {

    val data = MutableLiveData<JobItem>()

}