package br.com.ufg.www.events.data.offline.dao

import androidx.room.Dao
import androidx.room.Query
import br.com.ufg.www.events.data.model.User
import br.com.ufg.www.events.data.offline.entities.UserEntity
import org.intellij.lang.annotations.Language

@Dao
interface UserDao : BaseDAO<UserEntity> {

    @Language("RoomSql")
    @Query("SELECT * FROM users order by email")
    fun readAll(): List<UserEntity>

    @Language("RoomSql")
    @Query("SELECT id, email FROM users where email like :email and password like :passwordUpperAndHashed limit 1")
    fun login(email: String, passwordUpperAndHashed: String): User?

    @Language("RoomSql")
    @Query("SELECT id, email FROM users where id = :idUser")
    fun getUser(idUser: Long): User?

}