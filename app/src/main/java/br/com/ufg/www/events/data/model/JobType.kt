package br.com.ufg.www.events.data.model

import android.os.Parcelable
import br.com.ufg.www.events.data.offline.entities.EventWithJobTypeEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class JobType(
        val id: Long,
        val description: String
) : Parcelable {

    fun toEventWithJobTypesEntity(idEvent: Long) = EventWithJobTypeEntity(
            idEvent = idEvent,
            idJobType = id
    )

}