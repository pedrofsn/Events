package br.com.pedrofsn.jobs.jobs.ui.list

import br.com.pedrofsn.jobs.R
import br.com.pedrofsn.jobs.databinding.ViewholderJobBinding
import br.com.pedrofsn.jobs.domain.BaseAdapterMVVM
import br.com.pedrofsn.jobs.jobs.data.model.JobItem

/*
    CREATED BY @PEDROFSN IN 24/10/20 12:17
*/

class JobAdapter(override var click: ((JobItem, Int) -> Unit)? = null) :
    BaseAdapterMVVM<JobItem, ViewholderJobBinding>() {

    override val layout: Int = R.layout.viewholder_job

    override fun getViewHolder(binding: ViewholderJobBinding): JobViewHolder {
        return JobViewHolder(binding)
    }

}