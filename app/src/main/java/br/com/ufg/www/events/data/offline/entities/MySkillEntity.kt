package br.com.ufg.www.events.data.offline.entities

import androidx.room.*


@Entity(
        tableName = "my_skills",
        foreignKeys = arrayOf(
                ForeignKey(
                        entity = SkillEntity::class,
                        parentColumns = arrayOf("skill_id"),
                        childColumns = arrayOf("skill_id"),
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE)
        ),
        indices = [
            Index(
                    value = ["skill_id"],
                    unique = true)
        ]
)
class MySkillEntity(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        @ColumnInfo(name = "skill_id") val idSkill: Long
)