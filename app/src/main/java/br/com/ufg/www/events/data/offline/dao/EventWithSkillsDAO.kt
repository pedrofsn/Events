package br.com.ufg.www.events.data.offline.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.ufg.www.events.data.offline.entities.EventWithSkillsEntity
import org.intellij.lang.annotations.Language

@Dao
interface EventWithSkillsDAO : BaseDAO<EventWithSkillsEntity> {

    @Language("RoomSql")
    @Query("delete from event_with_skills where event_id = :idEvent")
    fun delete(idEvent: Long)

    @Transaction
    fun insertAll(idEvent: Long, vararg entities: EventWithSkillsEntity) {
        delete(idEvent)
        insert(*entities)
    }

}