package br.com.pedrofsn.jobs.features.list.adapter

import br.com.pedrofsn.jobs.R
import br.com.pedrofsn.jobs.data.model.JobItem
import br.com.pedrofsn.jobs.databinding.ViewholderJobBinding
import br.com.pedrofsn.jobs.domain.BaseViewHolderMVVM

class JobViewHolder(binding: ViewholderJobBinding) :
    BaseViewHolderMVVM<JobItem, ViewholderJobBinding>(binding) {

    override fun bind(data: JobItem) {
        binding.data = data
        setupSubtitle(data)
    }

    private fun setupSubtitle(data: JobItem) {
        binding.textViewSubtitle.text = getSubtitle(days = data.countDaysFromDateUntilNow())
    }

    private fun getSubtitle(days: Int): String {
        val resources = binding.root.context.resources
        return when (days) {
            -1 -> resources.getString(R.string.posted_unknown)
            0 -> resources.getString(R.string.posted_today)
            else -> resources.getQuantityString(R.plurals.posted_since, days, days)
        }
    }
}