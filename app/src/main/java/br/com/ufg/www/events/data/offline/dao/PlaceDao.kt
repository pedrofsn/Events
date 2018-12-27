package br.com.ufg.www.events.data.offline.dao

import androidx.room.Dao
import androidx.room.Query
import br.com.ufg.www.events.data.model.Place
import br.com.ufg.www.events.data.offline.entities.PlaceEntity
import org.intellij.lang.annotations.Language

@Dao
interface PlaceDao : BaseDAO<PlaceEntity> {

    @Language("RoomSql")
    @Query("SELECT id, latitude, longitude, address FROM places where user_id like :idUser")
    fun readAll(idUser: Long): List<Place>

}