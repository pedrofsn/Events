package br.com.ufg.www.places.mvp.places.list.adapter

import android.view.View
import br.com.ufg.www.places.R
import br.com.ufg.www.places.domain.BaseAdapter
import br.com.ufg.www.places.domain.MyOnItemClickListener
import br.com.ufg.www.places.model.Place

class AdapterPlace(override var click: MyOnItemClickListener<Place>?) : BaseAdapter<Place, ViewHolderPlace>() {
    override val layout: Int = R.layout.adapter_place
    override fun getViewHolder(view: View) = ViewHolderPlace(view)
}