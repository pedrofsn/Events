package br.com.pedrofsn.jobs.features.list.adapter

import br.com.pedrofsn.jobs.data.model.JobItem
import br.com.pedrofsn.jobs.databinding.ViewholderJobBinding
import br.com.pedrofsn.jobs.domain.BaseViewHolderMVVM

class JobViewHolder(binding: ViewholderJobBinding) :
    BaseViewHolderMVVM<JobItem, ViewholderJobBinding>(binding) {

    override fun bind(data: JobItem) {
        binding.data = data
    }
}