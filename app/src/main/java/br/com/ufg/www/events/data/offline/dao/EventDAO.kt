package br.com.ufg.www.events.data.offline.dao

import androidx.room.Dao
import androidx.room.Query
import br.com.ufg.www.events.data.model.Event
import br.com.ufg.www.events.data.offline.entities.EventEntity
import br.com.ufg.www.events.data.ui.EventFull
import org.intellij.lang.annotations.Language

@Dao
interface EventDAO : BaseDAO<EventEntity> {

    @Language("RoomSql")
    @Query("""SELECT
         e.id as idEvent, e.name, e.date_start as dateStart, e.date_end as dateEnd,
         p.place_id as id, p.user_id as idUser, p.latitude, p.longitude, p.address
         FROM events e
         INNER JOIN places p ON p.place_id = e.place_id
         order by date_start asc""")
    fun getAllEvents(): List<EventFull>

    @Language("RoomSql")
    @Query("SELECT id, name, date_start as dateStart, date_end as dateEnd, place_id as idPlace FROM events where user_id like :idUser order by date_start asc")
    fun readAll(idUser: Long): List<Event>

    @Language("RoomSql")
    @Query("SELECT id, name, date_start as dateStart, date_end as dateEnd, place_id as idPlace FROM events where place_id = :idEvent")
    fun read(idEvent: Long): Event?

}