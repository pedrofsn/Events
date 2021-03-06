package br.com.pedrofsn.jobs.features.list

interface JobsView {
    fun observeProgressChanges()
    fun observeDataChanges()

    fun hideRefresh()
    fun showRefresh()

    fun load()
}