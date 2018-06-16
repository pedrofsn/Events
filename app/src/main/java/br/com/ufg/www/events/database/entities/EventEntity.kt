package br.com.ufg.www.events.database.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "events")
class EventEntity(
        @PrimaryKey(autoGenerate = false) val id: Long = 0,
        val latitude: Double,
        val longitude: Double,
        val description: String?,
        @ColumnInfo(name = "login") val userLogin: String
)