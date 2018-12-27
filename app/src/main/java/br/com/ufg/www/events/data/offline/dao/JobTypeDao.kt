package br.com.ufg.www.events.data.offline.dao

import androidx.room.Dao
import androidx.room.Query
import br.com.ufg.www.events.data.offline.entities.JobTypeEntity
import org.intellij.lang.annotations.Language

@Dao
interface JobTypeDao : BaseDAO<JobTypeEntity> {

    @Language("RoomSql")
    @Query("SELECT * FROM job_types order by description")
    fun readAll(): List<JobTypeEntity>

}