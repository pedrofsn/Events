package br.com.ufg.www.events.mvp.places.list.adapter

import android.view.View
import br.com.redcode.easyrecyclerview.library.adapter.BaseAdapter
import br.com.ufg.www.events.R
import br.com.ufg.www.events.model.Place

class AdapterPlace(override var click: ((Place, Int) -> Unit)?) : BaseAdapter<Place, ViewHolderPlace>() {
    override val layout: Int = R.layout.adapter_place
    override fun getViewHolder(view: View) = ViewHolderPlace(view)
}