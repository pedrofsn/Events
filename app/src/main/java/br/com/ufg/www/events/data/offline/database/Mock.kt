package br.com.ufg.www.events.data.offline.database

import br.com.redcode.base.extensions.toUpperCaseAndApplySHA256
import br.com.ufg.www.events.data.offline.entities.*
import java.util.*

object Mock {

    private val mockUser = UserEntity(
            id = 1,
            email = "pedrokra@gmail.com",
            passwordUpperAndHashed = "pedrokra@gmail.com".toUpperCaseAndApplySHA256()
    )

    private val mockSkill1 = SkillEntity(id = 1, description = "Garçom")
    private val mockSkill2 = SkillEntity(id = 2, description = "Churrasqueiro")
    private val mockSkill3 = SkillEntity(id = 3, description = "Animador de festa infantil")

    private val mockPlace = PlaceEntity(1, mockUser.id, "-16.650307", "-49.228746", "Parque Beija Flor")
    private val mockEventEntity = EventEntity(1, mockPlace.idUser, mockPlace.id, Date(), Date(), "Festa do Dia")

    private val mockEventWithSkillEntity1 = EventWithSkillsEntity(1, mockEventEntity.id, mockSkill1.id)
    private val mockEventWithSkillEntity2 = EventWithSkillsEntity(2, mockEventEntity.id, mockSkill2.id)

    private val mockMySkill1 = MySkillEntity(id = 1, idSkill = mockSkill1.id)

    fun seedDatabase() {
        AppDatabase.getInstance().userDAO().insert(mockUser)
        AppDatabase.getInstance().skillDAO().insert(mockSkill1, mockSkill2, mockSkill3)
        AppDatabase.getInstance().placeDAO().insert(mockPlace)
        AppDatabase.getInstance().eventDAO().insert(mockEventEntity)
        AppDatabase.getInstance().eventWithSkillsDAO().insert(mockEventWithSkillEntity1, mockEventWithSkillEntity2)
        AppDatabase.getInstance().mySkillsDAO().insert(mockMySkill1)
    }
}