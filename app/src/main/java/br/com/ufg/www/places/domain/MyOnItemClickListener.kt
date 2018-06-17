package br.com.ufg.www.places.domain

/**
 * Created by pedrofsn on 16/10/2017.
 */
interface MyOnItemClickListener<T> {

    fun onItemClickListener(item: T, position: Int)

}