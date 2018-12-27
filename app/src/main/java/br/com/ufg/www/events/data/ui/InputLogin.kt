package br.com.ufg.www.events.data.ui

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import br.com.redcode.base.extensions.toUpperCaseAndApplySHA256
import br.com.redcode.base.utils.Constants.EMPTY_STRING
import br.com.ufg.www.events.data.offline.entities.UserEntity

data class InputLogin(
        val nothing: String = EMPTY_STRING
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