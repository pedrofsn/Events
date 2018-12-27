package br.com.ufg.www.events.view.places.list.adapter

import br.com.redcode.base.mvvm.domain.adapter.BaseAdapterMVVM
import br.com.ufg.www.events.R
import br.com.ufg.www.events.data.model.Place
import br.com.ufg.www.events.databinding.ViewholderPlaceBinding

class AdapterPlace(override var click: ((Place, Int) -> Unit)?) : BaseAdapterMVVM<Place, ViewholderPlaceBinding>() {
    override val layout: Int = R.layout.viewholder_place
    override fun getViewHolder(binding: ViewholderPlaceBinding) = ViewHolderPlace(binding)
}