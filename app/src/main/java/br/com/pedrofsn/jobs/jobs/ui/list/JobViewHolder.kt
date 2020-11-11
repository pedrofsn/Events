package br.com.pedrofsn.jobs.jobs.ui.list

import br.com.pedrofsn.jobs.databinding.ViewholderJobBinding
import br.com.pedrofsn.jobs.domain.BaseViewHolderMVVM
import br.com.pedrofsn.jobs.jobs.data.model.JobItem

/*
    CREATED BY @PEDROFSN IN 24/10/20 12:18
*/

class JobViewHolder(binding: ViewholderJobBinding) :
    BaseViewHolderMVVM<JobItem, ViewholderJobBinding>(binding) {

    override fun bind(data: JobItem) {
        binding.data = data
    }

}