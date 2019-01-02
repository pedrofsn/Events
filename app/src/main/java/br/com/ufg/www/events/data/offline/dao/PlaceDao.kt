package br.com.ufg.www.events.data.offline.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import br.com.ufg.www.events.data.model.Place
import br.com.ufg.www.events.data.offline.entities.PlaceEntity
import org.intellij.lang.annotations.Language

@Dao
interface PlaceDao : BaseDAO<PlaceEntity> {

    @Language("RoomSql")
    @Query("SELECT place_id as idPlace, latitude, longitude, address FROM places where user_id like :idUser")
    fun readAll(idUser: Long): LiveData<List<Place>>

    @Language("RoomSql")
    @Query("SELECT place_id as idPlace, latitude, longitude, address FROM places where place_id = :idPlace")
    fun read(idPlace: Long): Place?

}