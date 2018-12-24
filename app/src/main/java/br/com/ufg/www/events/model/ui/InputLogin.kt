package br.com.ufg.www.events.model.ui

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import br.com.redcode.base.utils.Constants.EMPTY_STRING
import br.com.ufg.www.events.database.entities.UserEntity
import br.com.ufg.www.events.extensions.toUpperCaseAndApplySHA256

data class InputLogin(
        val nothing: String
) : BaseObservable() {

    @get:Bindable
    var login: String = EMPTY_STRING

    @get:Bindable
    var password: String = EMPTY_STRING

    fun toEntity() = UserEntity(
            email = login,
            passwordUpperAndHashed = password.toUpperCaseAndApplySHA256()
    )
}