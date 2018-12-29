package br.com.ufg.www.events.data.offline.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*


@Entity(
        tableName = "events",
        foreignKeys = arrayOf(
                ForeignKey(
                        entity = UserEntity::class,
                        parentColumns = arrayOf("id"),
                        childColumns = arrayOf("user_id"),
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE),
                ForeignKey(
                        entity = PlaceEntity::class,
                        parentColumns = arrayOf("place_id"),
                        childColumns = arrayOf("place_id"),
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE)
        )
)
class EventEntity(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        @ColumnInfo(name = "user_id") val idUser: Long,
        @ColumnInfo(name = "place_id") val idPlace: Long,
        @ColumnInfo(name = "date_start") val dateStart: Date,
        @ColumnInfo(name = "date_end") val dateEnd: Date,
        val name: String
)