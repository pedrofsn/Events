package br.com.pedrofsn.jobs.domain

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import java.util.*

abstract class BaseAdapterMVVM<Data, Binding : ViewDataBinding> :
    RecyclerView.Adapter<BaseViewHolderMVVM<Data, Binding>>() {

    abstract var click: ((Data, Int) -> Unit)?
    private val items = ArrayList<Data>()

    abstract val layout: Int

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolderMVVM<Data, Binding> {
        val inflater = LayoutInflater.from(parent.context)
        val binding: Binding = DataBindingUtil.inflate(inflater, layout, parent, false)
        return getViewHolder(binding)
    }

    abstract fun getViewHolder(binding: Binding): BaseViewHolderMVVM<Data, Binding>

    override fun onBindViewHolder(holder: BaseViewHolderMVVM<Data, Binding>, position: Int) {
        holder.bind(data = items[position], onClick = click)
    }

    override fun getItemCount() = items.size

    fun getList() = items

    open fun setCustomList(customList: List<Data>?) {
        if (customList != null) {
            if (items.isNotEmpty()) {
                items.clear()
            }
            items.addAll(customList)
            notifyDataSetChanged()
        }
    }

    fun addAll(newList: List<Data>?) {
        if (newList != null) {
            if (itemCount == 0) {
                setCustomList(newList)
            } else {
                val sizeCurrent = this.items.size
                val sizeNew = newList.size
                val total = sizeCurrent + sizeNew

                this.items.addAll(newList)
                notifyItemRangeInserted(sizeCurrent, total)
            }
        }
    }

    fun add(item: Data?) {
        if (item != null) {
            val sizeCurrent = this.items.size
            val total = sizeCurrent + 1

            this.items.add(item)
            notifyItemRangeInserted(sizeCurrent, total)
        }
    }

    fun isEmpty() = itemCount == 0

    open fun clear() {
        this.items.clear()
        notifyDataSetChanged()
    }

    fun getItem(index: Int) = items[index]
}