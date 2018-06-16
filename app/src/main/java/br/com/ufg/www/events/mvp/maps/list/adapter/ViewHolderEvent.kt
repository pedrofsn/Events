package br.com.ufg.www.events.mvp.maps.list.adapter

import android.view.View
import android.widget.TextView
import br.com.ufg.www.events.R
import br.com.ufg.www.events.domain.BaseViewHolder
import br.com.ufg.www.events.model.Event

class ViewHolderEvent(itemView: View) : BaseViewHolder<Event>(itemView) {

    private val textView by lazy { itemView.findViewById<TextView>(R.id.textView) }

    override fun popular(obj: Event) {
        textView.text = obj.toString()
    }

}