package br.com.ufg.www.events.data.offline.database

import br.com.redcode.base.extensions.toUpperCaseAndApplySHA256
import br.com.ufg.www.events.data.offline.entities.JobTypeEntity
import br.com.ufg.www.events.data.offline.entities.UserEntity

object Mock {

    private val mockUser = UserEntity(
            id = 1,
            email = "pedrokra@gmail.com",
            passwordUpperAndHashed = "pedrokra@gmail.com".toUpperCaseAndApplySHA256()
    )

    private val mockJobType1 = JobTypeEntity(id = 1, description = "Garçom")
    private val mockJobType2 = JobTypeEntity(id = 2, description = "Churrasqueiro")
    private val mockJobType3 = JobTypeEntity(id = 3, description = "Animador de festa infantil")

    fun seedDatabase() {
        AppDatabase.getInstance().userDAO().insert(mockUser)
        AppDatabase.getInstance().jobTypesDAO().insert(mockJobType1, mockJobType2, mockJobType3)
    }
}