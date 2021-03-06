package br.com.pedrofsn.jobs.features.list.adapter

import br.com.pedrofsn.jobs.R
import br.com.pedrofsn.jobs.data.model.JobItem
import br.com.pedrofsn.jobs.databinding.ViewholderJobBinding
import br.com.pedrofsn.jobs.domain.view.BaseAdapterMVVM

class JobAdapter(override var click: ((JobItem, Int) -> Unit)? = null) :
    BaseAdapterMVVM<JobItem, ViewholderJobBinding>() {

    override val layout: Int = R.layout.viewholder_job

    override fun getViewHolder(binding: ViewholderJobBinding): JobViewHolder {
        return JobViewHolder(binding)
    }
}