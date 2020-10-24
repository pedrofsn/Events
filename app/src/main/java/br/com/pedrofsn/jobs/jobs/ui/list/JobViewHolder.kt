package br.com.pedrofsn.jobs.jobs.ui.list

import android.view.View
import android.widget.TextView
import br.com.pedrofsn.jobs.R
import br.com.pedrofsn.jobs.jobs.data.model.JobItem
import br.com.redcode.easyrecyclerview.library.adapter.viewholder.BaseViewHolder

/*
    CREATED BY @PEDROFSN IN 24/10/20 12:18
*/

class JobViewHolder(itemView: View) : BaseViewHolder<JobItem>(itemView) {

    private val textViewTitle by lazy { itemView.findViewById<TextView>(R.id.textViewTitle) }
    private val textViewDescription by lazy { itemView.findViewById<TextView>(R.id.textViewDescription) }

    override fun populate(obj: JobItem) {
        textViewTitle.text = obj.title
        textViewDescription.text = obj.description
    }

}
