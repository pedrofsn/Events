package br.com.ufg.www.events.data.offline.dao

import androidx.room.Dao
import androidx.room.Query
import br.com.ufg.www.events.data.model.JobType
import br.com.ufg.www.events.data.offline.entities.JobTypeEntity
import org.intellij.lang.annotations.Language

@Dao
interface JobTypeDao : BaseDAO<JobTypeEntity> {

    @Language("RoomSql")
    @Query("SELECT id, description, 0 > 1 as selected FROM job_types order by description")
    fun readAll(): List<JobType>

    @Language("RoomSql")
    @Query("SELECT j.id, j.description, (select count(*) > 0 from event_with_job_type ew where ew.event_id = :idEvent and ew.job_type_id = j.id) as selected FROM job_types j order by description asc")
    fun readAll(idEvent: Long): List<JobType>

}