package br.com.ufg.www.events.domain

import android.view.View

/**
 * Created by pedrofsn on 16/10/2017.
 */
abstract class BaseViewHolder<Objeto>(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

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