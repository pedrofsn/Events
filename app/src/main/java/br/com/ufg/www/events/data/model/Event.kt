package br.com.ufg.www.events.data.model

import br.com.redcode.base.utils.Constants.SDF_BRAZILIAN_DATE
import java.util.*

data class Event(
        val id: Long,
        val name: String,
        val date: Date
) {
    fun getDateFormmated() = SDF_BRAZILIAN_DATE.format(date)
}