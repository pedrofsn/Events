package br.com.pedrofsn.jobs.domain

import android.content.Context
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolderMVVM<Data, B : ViewDataBinding>(val binding: B) :
    RecyclerView.ViewHolder(binding.root) {

    protected fun context(): Context = binding.root.context

    abstract fun bind(data: Data)

    open fun bind(data: Data, onClick: ((Data, Int) -> Unit)?) {
        bind(data)
        click(binding.root, data, onClick)
    }

    open fun click(view: View?, data: Data, onClick: ((Data, Int) -> Unit)?) {
        view?.setOnClickListener { onClick?.invoke(data, adapterPosition) }
    }
}