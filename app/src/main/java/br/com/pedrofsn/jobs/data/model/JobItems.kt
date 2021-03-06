package br.com.pedrofsn.jobs.data.model

import br.com.pedrofsn.jobs.domain.pagination.Paginated

data class JobItems(
    override val data: List<JobItem> = emptyList(),
    override val totalInAllPages: Int = 0
) : Paginated<JobItem>()