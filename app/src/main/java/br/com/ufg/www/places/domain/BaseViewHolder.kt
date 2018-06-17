package br.com.ufg.www.places.domain

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by pedrofsn on 16/10/2017.
 */
abstract class BaseViewHolder<Objeto>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun popular(obj: Objeto)

    open fun popular(obj: Objeto, click: MyOnItemClickListener<Objeto>?, clickRow: Boolean = true) {
        if (obj != null) {
            popular(obj)

            if (clickRow) {
                click(itemView, obj, click)
            }
        }
    }

    fun click(view: View, obj: Objeto, click: MyOnItemClickListener<Objeto>?) {
        click?.let { view.setOnClickListener { click.onItemClickListener(obj, adapterPosition) } }
    }

}