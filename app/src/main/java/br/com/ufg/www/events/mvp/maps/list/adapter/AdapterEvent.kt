package br.com.ufg.www.events.mvp.maps.list.adapter

import android.view.View
import br.com.ufg.www.events.R
import br.com.ufg.www.events.domain.BaseAdapter
import br.com.ufg.www.events.domain.MyOnItemClickListener
import br.com.ufg.www.events.model.Event

class AdapterEvent(override var click: MyOnItemClickListener<Event>?) : BaseAdapter<Event, ViewHolderEvent>() {
    override val layout: Int = R.layout.adapter_event
    override fun getViewHolder(view: View) = ViewHolderEvent(view)
}