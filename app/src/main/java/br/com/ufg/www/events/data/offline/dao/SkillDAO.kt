package br.com.ufg.www.events.data.offline.dao

import androidx.room.Dao
import androidx.room.Query
import br.com.ufg.www.events.data.model.Skill
import br.com.ufg.www.events.data.offline.entities.SkillEntity
import org.intellij.lang.annotations.Language

@Dao
interface SkillDAO : BaseDAO<SkillEntity> {

    @Language("RoomSql")
    @Query("SELECT skill_id as idSkill, description, 0 > 1 as selected FROM skills order by description")
    fun getAllSkills(): List<Skill>

    @Language("RoomSql")
    @Query("SELECT j.skill_id as idSkill, j.description, (select count(*) > 0 from event_with_skills ew where ew.event_id = :idEvent and ew.skill_id = j.skill_id) as selected FROM skills j order by description asc")
    fun getSkillsSelectedsAndUnselecteds(idEvent: Long): List<Skill>

    @Language("RoomSql")
    @Query("SELECT j.skill_id as idSkill, j.description, 1 > 0  as selected FROM skills j WHERE j.skill_id in (select skill_id from event_with_skills ew where ew.event_id = :idEvent and ew.skill_id = j.skill_id) ORDER BY description ASC")
    fun getSkillsSelecteds(idEvent: Long): List<Skill>

    @Language("RoomSql")
    @Query("SELECT j.skill_id as idSkill, j.description, (select count(*) > 0 from my_skills ew where ew.skill_id = j.skill_id) as selected FROM skills j order by description asc")
    fun getMySkills(): List<Skill>

}