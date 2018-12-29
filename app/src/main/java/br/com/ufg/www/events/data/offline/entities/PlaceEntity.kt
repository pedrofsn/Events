package br.com.ufg.www.events.data.offline.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import br.com.ufg.www.events.data.model.Place


@Entity(
        tableName = "places",
        foreignKeys = arrayOf(
                ForeignKey(
                        entity = UserEntity::class,
                        parentColumns = arrayOf("user_id"),
                        childColumns = arrayOf("user_id"),
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE)
        )
)
class PlaceEntity(
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "place_id") val idPlace: Long = 0,
        @ColumnInfo(name = "user_id") val idUser: Long,
        val latitude: String,
        val longitude: String,
        val address: String?
) {
    fun toModel() = Place(
            idPlace = idPlace,
            latitude = latitude,
            longitude = longitude,
            address = address
    )
}