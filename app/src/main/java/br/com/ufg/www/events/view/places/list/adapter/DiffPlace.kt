package br.com.ufg.www.events.view.places.list.adapter

import androidx.recyclerview.widget.DiffUtil
import br.com.ufg.www.events.data.model.Place

class DiffPlace(
        val oldPersons: List<Place>?,
        val newPersons: List<Place>?
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldPersons?.size ?: 0

    override fun getNewListSize() = newPersons?.size ?: 0

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldPersons?.get(oldItemPosition)?.equals(newPersons?.get(newItemPosition)) ?: false
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldPersons?.get(oldItemPosition)?.idPlace == newPersons?.get(newItemPosition)?.idPlace
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }

}