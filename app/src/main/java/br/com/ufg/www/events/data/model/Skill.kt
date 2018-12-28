package br.com.ufg.www.events.data.model

import br.com.ufg.www.events.data.offline.entities.EventWithSkillsEntity

data class Skill(
        val id: Long,
        val description: String,

        var selected: Boolean = false
) {

    fun toEntity(idEvent: Long) = EventWithSkillsEntity(
            idEvent = idEvent,
            idSkill = id
    )

}