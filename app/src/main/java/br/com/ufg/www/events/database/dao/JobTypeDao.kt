package br.com.ufg.www.events.database.dao

import androidx.room.Dao
import androidx.room.Query
import br.com.ufg.www.events.database.entities.JobTypeEntity
import org.intellij.lang.annotations.Language

@Dao
interface JobTypeDao : BaseDAO<JobTypeEntity> {

    @Language("RoomSql")
    @Query("SELECT * FROM job_types order by description")
    fun readAll(): List<JobTypeEntity>

}