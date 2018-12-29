package br.com.ufg.www.events.data.offline.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "skills")
class SkillEntity(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "skill_id")
        val id: Long = 0,

        val description: String
)