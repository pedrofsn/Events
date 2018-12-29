package br.com.ufg.www.events.data.offline.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.ufg.www.events.data.model.Event
import br.com.ufg.www.events.data.model.EventFull
import br.com.ufg.www.events.data.offline.database.AppDatabase
import br.com.ufg.www.events.data.offline.entities.EventEntity
import br.com.ufg.www.events.data.offline.entities.EventFullEntity
import org.intellij.lang.annotations.Language

@Dao
interface EventDAO : BaseDAO<EventEntity> {

    @Language("RoomSql")
    @Query("""SELECT
         e.event_id, e.name, e.date_start, e.date_end,
         p.place_id, p.user_id, p.latitude, p.longitude, p.address
         FROM events e
         INNER JOIN places p ON p.place_id = e.place_id
         order by date_start asc""")
    fun getAllEvents(): List<EventFullEntity>

    @Transaction
    fun getAllEventFull(): List<EventFull> {
        val eventsFull = arrayListOf<EventFull>()
        val eventsFullEntity = getAllEvents()
        eventsFullEntity.forEach { event ->
            val skills = AppDatabase.getInstance().skillDAO().getSkillsSelecteds(event.event_id)
            val place = event.place.toModel()

            val model = EventFull(
                    idEvent = event.event_id,
                    name = event.name,
                    dateStart = event.date_start,
                    dateEnd = event.date_end,
                    place = place,
                    skills = skills
            )
            eventsFull.add(model)
        }

        return eventsFull
    }


    @Language("RoomSql")
    @Query("SELECT event_id as idEvent, name, date_start as dateStart, date_end as dateEnd, place_id as idPlace FROM events where user_id like :idUser order by date_start asc")
    fun readAll(idUser: Long): List<Event>

    @Language("RoomSql")
    @Query("SELECT event_id as idEvent, name, date_start as dateStart, date_end as dateEnd, place_id as idPlace FROM events where place_id = :idEvent")
    fun read(idEvent: Long): Event?

}