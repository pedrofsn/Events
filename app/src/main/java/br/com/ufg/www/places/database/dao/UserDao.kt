package br.com.ufg.www.places.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import br.com.ufg.www.places.database.entities.UserEntity
import org.intellij.lang.annotations.Language

@Dao
interface UserDao : BaseDAO<UserEntity> {

    @Language("RoomSql")
    @Query("SELECT count(*) == 1 FROM users where email like :email and password like :passwordUpperAndHashed ")
    fun isLoginValid(email: String, passwordUpperAndHashed: String): Boolean

}