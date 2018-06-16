package br.com.ufg.www.events.model

import br.com.ufg.www.events.database.entities.UserEntity
import br.com.ufg.www.events.extensions.toUpperCaseAndApplySHA256

data class RegisterUser(
        val login: String,
        val password: String
) {
    fun toEntity() = UserEntity(
            email = login,
            passwordUpperAndHashed = password.toUpperCaseAndApplySHA256()
    )
}