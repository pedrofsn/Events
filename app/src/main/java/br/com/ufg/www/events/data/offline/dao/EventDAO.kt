package br.com.ufg.www.events.data.offline.dao

import androidx.room.Dao
import androidx.room.Query
import br.com.ufg.www.events.data.model.Event
import br.com.ufg.www.events.data.offline.entities.EventEntity
import org.intellij.lang.annotations.Language

@Dao
interface EventDAO : BaseDAO<EventEntity> {

    @Language("RoomSql")
    @Query("SELECT id, name, date, place_id as idPlace FROM events where user_id like :idUser order by date asc")
    fun readAll(idUser: Long): List<Event>

    @Language("RoomSql")
    @Query("SELECT id, name, date, place_id as idPlace FROM events where id = :idEvent")
    fun read(idEvent: Long): Event?

}