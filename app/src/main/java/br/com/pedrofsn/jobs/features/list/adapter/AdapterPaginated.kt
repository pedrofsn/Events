package br.com.pedrofsn.jobs.features.list.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import br.com.pedrofsn.jobs.data.model.WithID
import br.com.pedrofsn.jobs.domain.view.BaseViewHolderMVVM

abstract class AdapterPaginated<Data : WithID, B : ViewDataBinding>(
    diff: DiffUtil.ItemCallback<Data> = object : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data) = oldItem.id == newItem.id

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Data, newItem: Data) = oldItem == newItem
    }
) : PagedListAdapter<Data, BaseViewHolderMVVM<Data, B>>(diff) {

    abstract var onClick: ((Data, Int) -> Unit)?
    abstract val layout: Int
    abstract fun getViewHolder(binding: B): BaseViewHolderMVVM<Data, B>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolderMVVM<Data, B> {
        val inflater = LayoutInflater.from(parent.context)
        val binding: B = DataBindingUtil.inflate(inflater, layout, parent, false)
        return getViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolderMVVM<Data, B>, position: Int) {
        getItem(position)?.let { data -> holder.bind(data, onClick) }
    }
}