package br.com.ufg.www.events.database.dao

import androidx.room.Dao
import androidx.room.Query
import br.com.ufg.www.events.database.entities.PlaceEntity
import org.intellij.lang.annotations.Language

@Dao
interface PlaceDao : BaseDAO<PlaceEntity> {

    @Language("RoomSql")
    @Query("SELECT * FROM places where login like :userLogin")
    fun readAll(userLogin: String): List<PlaceEntity>

}