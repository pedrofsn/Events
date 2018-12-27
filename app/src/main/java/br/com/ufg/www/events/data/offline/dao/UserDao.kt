package br.com.ufg.www.events.data.offline.dao

import androidx.room.Dao
import androidx.room.Query
import br.com.ufg.www.events.data.offline.entities.UserEntity
import org.intellij.lang.annotations.Language

@Dao
interface UserDao : BaseDAO<UserEntity> {

    @Language("RoomSql")
    @Query("SELECT * FROM users order by email")
    fun readAll(): List<UserEntity>

    @Language("RoomSql")
    @Query("SELECT count(*) > 0 FROM users where email like :email and password like :passwordUpperAndHashed ")
    fun isLoginValid(email: String, passwordUpperAndHashed: String): Boolean

}