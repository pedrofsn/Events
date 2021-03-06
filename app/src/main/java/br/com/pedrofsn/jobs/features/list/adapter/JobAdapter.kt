package br.com.pedrofsn.jobs.features.list.adapter

import br.com.pedrofsn.jobs.R
import br.com.pedrofsn.jobs.data.model.JobItem
import br.com.pedrofsn.jobs.databinding.ViewholderJobBinding

class JobAdapter(
    override var onClick: ((JobItem, Int) -> Unit)?
) : AdapterPaginated<JobItem, ViewholderJobBinding>() {
    override val layout: Int = R.layout.viewholder_job
    override fun getViewHolder(binding: ViewholderJobBinding) = JobViewHolder(binding)
}