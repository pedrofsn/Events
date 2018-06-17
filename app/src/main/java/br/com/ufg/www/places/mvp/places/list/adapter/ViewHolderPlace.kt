package br.com.ufg.www.places.mvp.places.list.adapter

import android.view.View
import android.widget.TextView
import br.com.ufg.www.places.App
import br.com.ufg.www.places.R
import br.com.ufg.www.places.domain.BaseViewHolder
import br.com.ufg.www.places.model.Place

class ViewHolderPlace(itemView: View) : BaseViewHolder<Place>(itemView) {

    private val textViewDescription by lazy { itemView.findViewById<TextView>(R.id.textViewDescription) }
    private val textViewLatitude by lazy { itemView.findViewById<TextView>(R.id.textViewLatitude) }
    private val textViewLongitude by lazy { itemView.findViewById<TextView>(R.id.textViewLongitude) }

    override fun popular(obj: Place) {
        textViewDescription.text = obj.description
        textViewLatitude.text = "${App.instance.getString(R.string.latitude_two_dots)} ${obj.latitude}"
        textViewLongitude.text = "${App.instance.getString(R.string.longitude_two_dots)} ${obj.longitude}"
    }

}