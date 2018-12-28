package br.com.ufg.www.events.data.offline.entities

import androidx.room.*


@Entity(
        tableName = "event_with_job_type",
        foreignKeys = arrayOf(
                ForeignKey(
                        entity = EventEntity::class,
                        parentColumns = arrayOf("id"),
                        childColumns = arrayOf("event_id"),
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE),
                ForeignKey(
                        entity = JobTypeEntity::class,
                        parentColumns = arrayOf("id"),
                        childColumns = arrayOf("job_type_id"),
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE)
        ),
        indices = [
            Index(
                    value = ["event_id", "job_type_id"],
                    unique = true)
        ]
)
class EventWithJobTypeEntity(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        @ColumnInfo(name = "event_id") val idEvent: Long,
        @ColumnInfo(name = "job_type_id") val idJobType: Long
)