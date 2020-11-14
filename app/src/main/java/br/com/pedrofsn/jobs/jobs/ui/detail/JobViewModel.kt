package br.com.pedrofsn.jobs.jobs.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.pedrofsn.jobs.jobs.data.model.JobItem

/*
    CREATED BY @PEDROFSN IN 14/11/20 08:20
*/

class JobViewModel : ViewModel() {

    val data = MutableLiveData<JobItem>()

}