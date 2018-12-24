package br.com.ufg.www.events.view.places.list.adapter

import br.com.redcode.base.mvvm.domain.adapter.BaseViewHolderMVVM
import br.com.ufg.www.events.databinding.AdapterPlaceBinding
import br.com.ufg.www.events.model.Place

class ViewHolderPlace(binding: AdapterPlaceBinding) : BaseViewHolderMVVM<Place, AdapterPlaceBinding>(binding) {

    override fun bind(data: Place) {
        binding.data = data
    }

}