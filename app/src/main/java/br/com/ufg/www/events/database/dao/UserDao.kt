package br.com.ufg.www.events.database.dao

import androidx.room.Dao
import androidx.room.Query
import br.com.ufg.www.events.database.entities.UserEntity
import org.intellij.lang.annotations.Language

@Dao
interface UserDao : BaseDAO<UserEntity> {

    @Language("RoomSql")
    @Query("SELECT count(*) == 1 FROM users where email like :email and password like :passwordUpperAndHashed ")
    fun isLoginValid(email: String, passwordUpperAndHashed: String): Boolean

}