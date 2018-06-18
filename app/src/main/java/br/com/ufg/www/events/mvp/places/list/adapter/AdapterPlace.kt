package br.com.ufg.www.events.mvp.places.list.adapter

import android.view.View
import br.com.ufg.www.events.R
import br.com.ufg.www.events.domain.BaseAdapter
import br.com.ufg.www.events.domain.MyOnItemClickListener
import br.com.ufg.www.events.model.Place

class AdapterPlace(override var click: MyOnItemClickListener<Place>?) : BaseAdapter<Place, ViewHolderPlace>() {
    override val layout: Int = R.layout.adapter_place
    override fun getViewHolder(view: View) = ViewHolderPlace(view)
}