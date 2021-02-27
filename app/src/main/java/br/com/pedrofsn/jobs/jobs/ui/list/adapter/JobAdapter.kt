package br.com.pedrofsn.jobs.jobs.ui.list.adapter

import br.com.pedrofsn.jobs.R
import br.com.pedrofsn.jobs.databinding.ViewholderJobBinding
import br.com.pedrofsn.jobs.domain.BaseAdapterMVVM
import br.com.pedrofsn.jobs.jobs.data.model.JobItem

class JobAdapter(override var click: ((JobItem, Int) -> Unit)? = null) :
    BaseAdapterMVVM<JobItem, ViewholderJobBinding>() {

    override val layout: Int = R.layout.viewholder_job

    override fun getViewHolder(binding: ViewholderJobBinding): JobViewHolder {
        return JobViewHolder(binding)
    }

}