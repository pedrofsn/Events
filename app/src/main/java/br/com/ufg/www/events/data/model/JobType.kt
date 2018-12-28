package br.com.ufg.www.events.data.model

import br.com.ufg.www.events.data.offline.entities.EventWithJobTypeEntity

data class JobType(
        val id: Long,
        val description: String,

        var selected: Boolean = false
) {

    fun toEventWithJobTypesEntity(idEvent: Long) = EventWithJobTypeEntity(
            idEvent = idEvent,
            idJobType = id
    )

}