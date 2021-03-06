package br.com.pedrofsn.jobs.domain.pagination

import androidx.annotation.Keep

@Keep
abstract class Paginated<T> {

    abstract val data: List<T>
    abstract val totalInAllPages: Int
}