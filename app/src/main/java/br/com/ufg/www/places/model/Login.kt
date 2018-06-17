package br.com.ufg.www.places.model

import br.com.ufg.www.places.database.entities.UserEntity
import br.com.ufg.www.places.extensions.toUpperCaseAndApplySHA256

data class Login(
        val login: String,
        val password: String
) {
    fun toEntity() = UserEntity(
            email = login,
            passwordUpperAndHashed = password.toUpperCaseAndApplySHA256()
    )
}