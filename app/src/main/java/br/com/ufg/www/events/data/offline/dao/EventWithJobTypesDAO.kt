package br.com.ufg.www.events.data.offline.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.ufg.www.events.data.offline.entities.EventWithJobTypeEntity
import org.intellij.lang.annotations.Language

@Dao
interface EventWithJobTypesDAO : BaseDAO<EventWithJobTypeEntity> {

    @Language("RoomSql")
    @Query("delete from event_with_job_type where event_id = :idEvent")
    fun delete(idEvent: Long)

    @Transaction
    fun insertAll(idEvent: Long, vararg entities: EventWithJobTypeEntity) {
        delete(idEvent)
        insert(*entities)
    }

}