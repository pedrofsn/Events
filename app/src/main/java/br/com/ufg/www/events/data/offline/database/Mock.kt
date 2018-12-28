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

    private val mockJobType1 = JobTypeEntity(id = 1, description = "Garçom")
    private val mockJobType2 = JobTypeEntity(id = 2, description = "Churrasqueiro")
    private val mockJobType3 = JobTypeEntity(id = 3, description = "Animador de festa infantil")

    private val mockPlace = PlaceEntity(1, mockUser.id, "-16.650307", "-49.228746", "Parque Beija Flor")
    private val mockEventEntity = EventEntity(1, mockPlace.idUser, mockPlace.id, Date(), "Festa do Dia")

    private val mockEventWithJobTypeEntity1 = EventWithJobTypeEntity(1, mockEventEntity.id, mockJobType1.id)
    private val mockEventWithJobTypeEntity2 = EventWithJobTypeEntity(2, mockEventEntity.id, mockJobType2.id)

    fun seedDatabase() {
        AppDatabase.getInstance().userDAO().insert(mockUser)
        AppDatabase.getInstance().jobTypesDAO().insert(mockJobType1, mockJobType2, mockJobType3)
        AppDatabase.getInstance().placeDAO().insert(mockPlace)
        AppDatabase.getInstance().eventDAO().insert(mockEventEntity)
        AppDatabase.getInstance().eventWithJobTypeDAO().insert(mockEventWithJobTypeEntity1, mockEventWithJobTypeEntity2)
    }
}