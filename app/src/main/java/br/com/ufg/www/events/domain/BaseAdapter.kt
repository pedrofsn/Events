package br.com.ufg.www.events.domain

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.*

/**
 * Created by pedrofsn on 16/10/2017.
 */
abstract class BaseAdapter<Objeto, VH : BaseViewHolder<Objeto>>(var myOnItemClickListener: MyOnItemClickListener<Objeto>? = null) : RecyclerView.Adapter<VH>() {

    abstract var click: MyOnItemClickListener<Objeto>?
    private val original = arrayListOf<Objeto>()
    val list = ArrayList<Objeto>()
    open var clickRow = true

    abstract val layout: Int

    fun setOnItemClickListener(click: MyOnItemClickListener<Objeto>) {
        this.click = click
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(layout, parent, false)
        return getViewHolder(view)
    }

    abstract fun getViewHolder(view: View): VH

    override fun onBindViewHolder(holder: VH, position: Int) = holder.popular(obj = getLista()[position], click = click, clickRow = clickRow)

    override fun getItemCount() = list.size

    fun getLista() = list

    open fun setCustomList(lista: List<Objeto>?) {
        if (lista != null) {
            this.list.clear()
            this.list.addAll(lista)
            notifyDataSetChanged()
        }
    }

    fun addAll(novaLista: List<Objeto>?) {
        if (novaLista != null) {
            if (itemCount == 0) {
                setCustomList(novaLista)
            } else {
                val tamanhoAtual = this.list.size
                val tamanhoNovo = novaLista.size
                val total = tamanhoAtual + tamanhoNovo

                this.list.addAll(novaLista)
                notifyItemRangeInserted(tamanhoAtual, total)
            }
        }
    }

    fun add(item: Objeto?) {
        if (item != null) {
            val tamanhoAtual = this.list.size
            val total = tamanhoAtual + 1

            this.list.add(item)
            notifyItemRangeInserted(tamanhoAtual, total)
        }
    }

    fun isEmpty() = list.size == 0

    open fun clear() {
        this.list.clear()
        notifyDataSetChanged()
    }

    fun getItem(index: Int) = list[index]
}