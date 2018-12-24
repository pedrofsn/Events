package br.com.ufg.www.events.model.ui

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import br.com.redcode.base.utils.Constants
import br.com.ufg.www.events.database.entities.UserEntity
import br.com.ufg.www.events.extensions.toUpperCaseAndApplySHA256

data class InputUser(
        val nothing: String
) : BaseObservable() {

    @get:Bindable
    var login: String = Constants.EMPTY_STRING

    @get:Bindable
    var password: String = Constants.EMPTY_STRING

    @get:Bindable
    var confirmation: String = Constants.EMPTY_STRING

    fun toEntity() = UserEntity(
            email = login,
            passwordUpperAndHashed = password.toUpperCaseAndApplySHA256()
    )
}