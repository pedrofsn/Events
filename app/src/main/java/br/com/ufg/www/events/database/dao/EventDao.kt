package br.com.ufg.www.events.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import br.com.ufg.www.events.database.entities.EventEntity
import org.intellij.lang.annotations.Language

@Dao
interface EventDao : BaseDAO<EventEntity> {

    @Language("RoomSql")
    @Query("SELECT * FROM events where login like :userLogin")
    fun readAll(userLogin: String): List<EventEntity>

}