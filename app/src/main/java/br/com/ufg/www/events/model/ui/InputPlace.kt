package br.com.ufg.www.events.model.ui

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import br.com.redcode.base.extensions.extract
import br.com.redcode.base.utils.Constants
import br.com.ufg.www.events.App
import br.com.ufg.www.events.model.Place

data class InputPlace(val login: String) : BaseObservable() {

    @get:Bindable
    var latitude: String = Constants.EMPTY_STRING

    @get:Bindable
    var longitude: String = Constants.EMPTY_STRING

    @get:Bindable
    var description: String = Constants.EMPTY_STRING

    fun toModel() = Place(
            latitude = extract safe latitude,
            longitude = extract safe longitude,
            description = extract safe description,
            login = App.userLoggedIn
    )

}