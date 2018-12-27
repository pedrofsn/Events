package br.com.ufg.www.events.data.offline.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
        tableName = "users",
        indices = [
            Index(
                    value = ["email"],
                    unique = true)
        ]
)
class UserEntity(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        @ColumnInfo(name = "password") val passwordUpperAndHashed: String,
        val email: String
)