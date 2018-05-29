package br.com.ufg.www.events.database.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "users")
class UserEntity(
        @PrimaryKey(autoGenerate = false) val id: Long,
        @ColumnInfo(name = "password") val passwordUpperAndHashed: String,
        val email: String
)