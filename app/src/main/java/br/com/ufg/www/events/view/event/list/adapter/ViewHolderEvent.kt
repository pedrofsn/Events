package br.com.ufg.www.events.view.event.list.adapter

import br.com.redcode.base.mvvm.domain.adapter.BaseViewHolderMVVM
import br.com.ufg.www.events.data.model.Event
import br.com.ufg.www.events.databinding.ViewholderEventBinding

class ViewHolderEvent(binding: ViewholderEventBinding) : BaseViewHolderMVVM<Event, ViewholderEventBinding>(binding) {

    override fun bind(data: Event) {
        binding.data = data
    }

}