package br.com.ufg.www.events.data.offline.dao

import androidx.room.Dao
import androidx.room.Query
import br.com.ufg.www.events.data.model.Skill
import br.com.ufg.www.events.data.offline.entities.SkillEntity
import org.intellij.lang.annotations.Language

@Dao
interface SkillDAO : BaseDAO<SkillEntity> {

    @Language("RoomSql")
    @Query("SELECT id, description, 0 > 1 as selected FROM skills order by description")
    fun readAll(): List<Skill>

    @Language("RoomSql")
    @Query("SELECT j.id, j.description, (select count(*) > 0 from event_with_skills ew where ew.event_id = :idEvent and ew.skill_id = j.id) as selected FROM skills j order by description asc")
    fun readAll(idEvent: Long): List<Skill>

}