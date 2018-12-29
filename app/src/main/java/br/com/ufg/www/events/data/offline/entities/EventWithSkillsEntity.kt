package br.com.ufg.www.events.data.offline.entities

import androidx.room.*


@Entity(
        tableName = "event_with_skills",
        foreignKeys = arrayOf(
                ForeignKey(
                        entity = EventEntity::class,
                        parentColumns = arrayOf("event_id"),
                        childColumns = arrayOf("event_id"),
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE),
                ForeignKey(
                        entity = SkillEntity::class,
                        parentColumns = arrayOf("id"),
                        childColumns = arrayOf("skill_id"),
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE)
        ),
        indices = [
            Index(
                    value = ["event_id", "skill_id"],
                    unique = true)
        ]
)
class EventWithSkillsEntity(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        @ColumnInfo(name = "event_id") val idEvent: Long,
        @ColumnInfo(name = "skill_id") val idSkill: Long
)