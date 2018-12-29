package br.com.ufg.www.events.data.model

import java.util.*

data class EventFull(
        val idEvent: Long,
        val name: String,
        val dateStart: Date,
        val dateEnd: Date,

        val place: Place,

        val skills: List<Skill>
) {
    fun temp() = Event(
            idPlace = place.idPlace,
            idEvent = idEvent,
            name = name,
            dateStart = dateStart,
            dateEnd = dateEnd
    )
}