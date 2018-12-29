package br.com.ufg.www.events.data.offline.entities

import androidx.room.Embedded
import androidx.room.Relation
import java.util.*

data class EventFullEntity(
        val event_id: Long,
        val name: String,
        val date_start: Date,
        val date_end: Date,

        @Embedded
        val place: PlaceEntity,

        @Relation(
                parentColumn = "event_id",
                entityColumn = "event_id"
        )
        val skills: List<EventWithSkillsEntity>

)