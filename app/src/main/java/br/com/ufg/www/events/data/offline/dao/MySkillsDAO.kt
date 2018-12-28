package br.com.ufg.www.events.data.offline.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.ufg.www.events.data.offline.entities.MySkillEntity
import org.intellij.lang.annotations.Language

@Dao
interface MySkillsDAO : BaseDAO<MySkillEntity> {

    @Language("RoomSql")
    @Query("delete FROM my_skills")
    fun deleteAll()

    @Transaction
    fun insertAll(vararg entities: MySkillEntity) {
        deleteAll()
        insert(*entities)
    }

}