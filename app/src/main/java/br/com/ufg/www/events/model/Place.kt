package br.com.ufg.www.events.model

import android.os.Parcelable
import br.com.ufg.www.events.App
import br.com.ufg.www.events.R
import br.com.ufg.www.events.database.entities.PlaceEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Place(
        val latitude: String,
        val longitude: String,
        val description: String?,
        val login: String
) : Parcelable {
    override fun toString() = "$description\n${App.instance.getString(R.string.latitude)}: $latitude\t${App.instance.getString(R.string.longitude)}: $longitude"

    fun toEntity() = PlaceEntity(
            latitude = latitude,
            longitude = longitude,
            description = description,
            userLogin = login
    )
}