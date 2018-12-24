package br.com.ufg.www.events.mvp.places.list.adapter

import br.com.redcode.base.mvvm.domain.adapter.BaseAdapterMVVM
import br.com.ufg.www.events.R
import br.com.ufg.www.events.databinding.AdapterPlaceBinding
import br.com.ufg.www.events.model.Place

// TODO rename adapter and viewholder (including xml)
class AdapterPlace(override var click: ((Place, Int) -> Unit)?) : BaseAdapterMVVM<Place, AdapterPlaceBinding>() {
    override val layout: Int = R.layout.adapter_place
    override fun getViewHolder(binding: AdapterPlaceBinding) = ViewHolderPlace(binding)
}