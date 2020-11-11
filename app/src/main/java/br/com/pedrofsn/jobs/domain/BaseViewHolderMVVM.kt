package br.com.pedrofsn.jobs.domain

import android.content.Context
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by pedrofsn on 16/10/2017.
 */

abstract class BaseViewHolderMVVM<Data, B : ViewDataBinding>(private val binding: B) :
    RecyclerView.ViewHolder(binding.root) {

    protected fun context(): Context = binding.root.context

    open fun bind(data: Data) = binding.executePendingBindings()

    open fun bind(data: Data, onClick: ((Data, Int) -> Unit)?) {
        bind(data)
        click(binding.root, data, onClick)
    }

    open fun click(view: View?, data: Data, onClick: ((Data, Int) -> Unit)?) {
        view?.setOnClickListener { onClick?.invoke(data, adapterPosition) }
    }

}