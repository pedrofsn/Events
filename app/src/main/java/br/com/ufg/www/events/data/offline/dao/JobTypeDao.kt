package br.com.ufg.www.events.data.offline.dao

import androidx.room.Dao
import androidx.room.Query
import br.com.ufg.www.events.data.model.JobType
import br.com.ufg.www.events.data.offline.entities.JobTypeEntity
import org.intellij.lang.annotations.Language

@Dao
interface JobTypeDao : BaseDAO<JobTypeEntity> {

    @Language("RoomSql")
    @Query("SELECT id, description FROM job_types order by description")
    fun readAll(): List<JobType>

    @Language("RoomSql")
    @Query("SELECT id, description FROM job_types where id in (select id from event_with_job_type where event_id = :idEvent) order by description asc")
    fun readAll(idEvent: Long): List<JobType>

}