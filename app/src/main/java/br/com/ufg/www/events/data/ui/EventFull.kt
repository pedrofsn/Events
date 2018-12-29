package br.com.ufg.www.events.data.ui

import androidx.room.Embedded
import androidx.room.Relation
import br.com.redcode.base.utils.Constants.SDF_BRAZILIAN_DATE_AND_TIME
import br.com.ufg.www.events.data.model.Place
import br.com.ufg.www.events.data.offline.entities.EventWithSkillsEntity
import java.util.*

data class EventFull(
        val idEvent: Long,
        val name: String,
        val dateStart: Date,
        val dateEnd: Date,

        @Embedded
        val place: Place,

        @Relation(
                parentColumn = "idEvent",
                entityColumn = "event_id"
        )
        val skills: List<EventWithSkillsEntity>

) {
    fun getDateStartFormmated() = SDF_BRAZILIAN_DATE_AND_TIME.format(dateStart.time)
    fun getDateEndFormmated() = SDF_BRAZILIAN_DATE_AND_TIME.format(dateEnd.time)
    fun getDateStartAndEnd() = "${getDateStartFormmated()} - ${getDateEndFormmated()}"
}