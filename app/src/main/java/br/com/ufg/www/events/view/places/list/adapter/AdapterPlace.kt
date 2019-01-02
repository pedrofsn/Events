package br.com.ufg.www.events.view.places.list.adapter

import androidx.recyclerview.widget.DiffUtil
import br.com.redcode.base.mvvm.domain.adapter.BaseAdapterMVVM
import br.com.redcode.easyrecyclerview.library.extension_functions.clearAndAddAll
import br.com.ufg.www.events.R
import br.com.ufg.www.events.data.model.Place
import br.com.ufg.www.events.databinding.ViewholderPlaceBinding

class AdapterPlace(
        override var click: ((Place, Int) -> Unit)?
) : BaseAdapterMVVM<Place, ViewholderPlaceBinding>() {

    override val layout: Int = R.layout.viewholder_place
    override fun getViewHolder(binding: ViewholderPlaceBinding) = ViewHolderPlace(binding)

    fun setDiffList(customList: List<Place>?) {
        val diff = DiffPlace(oldPersons = items, newPersons = customList)
        val diffResult = DiffUtil.calculateDiff(diff)

        items.clearAndAddAll(customList)
        diffResult.dispatchUpdatesTo(this@AdapterPlace)
    }
}