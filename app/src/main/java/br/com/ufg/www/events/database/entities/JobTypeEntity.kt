package br.com.ufg.www.events.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.ufg.www.events.model.JobType


@Entity(tableName = "job_types")
class JobTypeEntity(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        val description: String
) {
    fun toModel() = JobType(
            id = id,
            description = description
    )
}