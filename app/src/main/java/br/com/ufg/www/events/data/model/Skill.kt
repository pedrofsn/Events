package br.com.ufg.www.events.data.model

import android.os.Parcelable
import br.com.ufg.www.events.data.offline.entities.EventWithSkillsEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Skill(
        val idSkill: Long,
        val description: String,

        var selected: Boolean = false
) : Parcelable {

    fun toEntity(idEvent: Long) = EventWithSkillsEntity(
            idEvent = idEvent,
            idSkill = idSkill
    )

}