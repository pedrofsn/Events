package br.com.ufg.www.events.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.ufg.www.events.model.Place


@Entity(tableName = "places")
class PlaceEntity(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        val latitude: String,
        val longitude: String,
        val address: String?,
        @ColumnInfo(name = "login") val userLogin: String
) {
    fun toModel() = Place(
            latitude = latitude,
            longitude = longitude,
            address = address,
            login = userLogin
    )
}