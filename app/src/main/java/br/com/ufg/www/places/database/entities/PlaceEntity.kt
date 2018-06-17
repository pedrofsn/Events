package br.com.ufg.www.places.database.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import br.com.ufg.www.places.model.Place


@Entity(tableName = "places")
class PlaceEntity(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        val latitude: Double,
        val longitude: Double,
        val description: String?,
        @ColumnInfo(name = "login") val userLogin: String
) {
    fun toModel() = Place(
            latitude = latitude,
            longitude = longitude,
            description = description,
            login = userLogin
    )
}