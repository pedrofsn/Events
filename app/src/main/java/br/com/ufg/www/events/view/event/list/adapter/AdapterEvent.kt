package br.com.ufg.www.events.view.event.list.adapter

import br.com.redcode.base.mvvm.domain.adapter.BaseAdapterMVVM
import br.com.ufg.www.events.R
import br.com.ufg.www.events.data.model.Event
import br.com.ufg.www.events.databinding.ViewholderEventBinding

class AdapterEvent(override var click: ((Event, Int) -> Unit)?) : BaseAdapterMVVM<Event, ViewholderEventBinding>() {
    override val layout: Int = R.layout.viewholder_event
    override fun getViewHolder(binding: ViewholderEventBinding) = ViewHolderEvent(binding)
}