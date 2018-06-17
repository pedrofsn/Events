package br.com.ufg.www.places.mvp.places.list.adapter

import android.view.View
import android.widget.TextView
import br.com.ufg.www.places.R
import br.com.ufg.www.places.domain.BaseViewHolder
import br.com.ufg.www.places.model.Place

class ViewHolderPlace(itemView: View) : BaseViewHolder<Place>(itemView) {

    private val textView by lazy { itemView.findViewById<TextView>(R.id.textView) }

    override fun popular(obj: Place) {
        textView.text = obj.toString()
    }

}