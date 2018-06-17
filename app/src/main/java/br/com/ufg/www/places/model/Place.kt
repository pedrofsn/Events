package br.com.ufg.www.places.model

import android.os.Parcelable
import br.com.ufg.www.places.App
import br.com.ufg.www.places.R
import br.com.ufg.www.places.database.entities.PlaceEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Place(
        val id: Long = 0,
        val latitude: Double,
        val longitude: Double,
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