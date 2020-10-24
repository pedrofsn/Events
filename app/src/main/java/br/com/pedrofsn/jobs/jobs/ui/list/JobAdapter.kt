package br.com.pedrofsn.jobs.jobs.ui.list

import android.view.View
import br.com.pedrofsn.jobs.R
import br.com.pedrofsn.jobs.jobs.data.model.JobItem
import br.com.redcode.easyrecyclerview.library.adapter.BaseAdapter

/*
    CREATED BY @PEDROFSN IN 24/10/20 12:17
*/

class JobAdapter(override var click: ((JobItem, Int) -> Unit)? = null) :
    BaseAdapter<JobItem, JobViewHolder>() {

    override val layout: Int = R.layout.viewholder_job
    override fun getViewHolder(view: View) = JobViewHolder(view)

}