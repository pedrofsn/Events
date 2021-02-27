package br.com.pedrofsn.jobs.jobs.ui.list.adapter

import br.com.pedrofsn.jobs.databinding.ViewholderJobBinding
import br.com.pedrofsn.jobs.domain.BaseViewHolderMVVM
import br.com.pedrofsn.jobs.jobs.data.model.JobItem

class JobViewHolder(binding: ViewholderJobBinding) :
    BaseViewHolderMVVM<JobItem, ViewholderJobBinding>(binding) {

    override fun bind(data: JobItem) {
        binding.data = data
    }

}