package br.com.ufg.www.events.data.model

import android.os.Parcelable
import br.com.ufg.www.events.App
import br.com.ufg.www.events.R
import br.com.ufg.www.events.data.offline.entities.PlaceEntity
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Place(
        val id: Long,
        val latitude: String,
        val longitude: String,
        val address: String?
) : Parcelable {
    override fun toString() = "$address\n${App.getContext()?.getString(R.string.latitude)}: $latitude\t${App.getContext()?.getString(R.string.longitude)}: $longitude"

    fun toEntity(idUser: Long) = PlaceEntity(
            latitude = latitude,
            longitude = longitude,
            address = address,
            idUser = idUser
    )

    fun toLatLng() = LatLng(latitude.toDouble(), longitude.toDouble())
    fun getLatitudeWithLabel() = "${App.getContext()?.getString(R.string.latitude_two_dots)} $latitude"
    fun getLongitudeWithLabel() = "${App.getContext()?.getString(R.string.longitude_two_dots)} $longitude"
    fun toPosition() = LatLng(latitude.toDouble(), longitude.toDouble())
}