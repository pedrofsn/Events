package br.com.ufg.www.events.data.ui

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import br.com.redcode.base.extensions.toUpperCaseAndApplySHA256
import br.com.redcode.base.utils.Constants
import br.com.ufg.www.events.data.offline.entities.UserEntity

data class InputUser(
        val nothing: String = Constants.EMPTY_STRING
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