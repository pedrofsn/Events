package br.com.ufg.www.events.data.offline.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
        tableName = "event_with_job_types",
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
        )
)
class EventWithJobTypesEntity(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        @ColumnInfo(name = "event_id") val idEvent: Long,
        @ColumnInfo(name = "job_type_id") val idJobType: Long
)