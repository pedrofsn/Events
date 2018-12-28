package br.com.ufg.www.events.data.model

import br.com.redcode.base.utils.Constants.SDF_BRAZILIAN_DATE_AND_TIME
import java.util.*

data class Event(
        val id: Long,
        val name: String,
        val dateStart: Date,
        val dateEnd: Date,
        val idPlace: Long
) {
    fun getDateStartFormmated() = SDF_BRAZILIAN_DATE_AND_TIME.format(dateStart.time)
    fun getDateEndFormmated() = SDF_BRAZILIAN_DATE_AND_TIME.format(dateEnd.time)
    fun getDateStartAndEnd() = "${getDateStartFormmated()} - ${getDateEndFormmated()}"
}