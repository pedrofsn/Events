package br.com.ufg.www.events.data.ui

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import br.com.redcode.base.extensions.extract
import br.com.redcode.base.utils.Constants
import br.com.ufg.www.events.data.offline.entities.PlaceEntity

data class InputPlace(val login: String) : BaseObservable() {

    @get:Bindable
    var latitude: String = Constants.EMPTY_STRING

    @get:Bindable
    var longitude: String = Constants.EMPTY_STRING

    @get:Bindable
    var address: String = Constants.EMPTY_STRING

    fun toPlaceEntity(idUser: Long) = PlaceEntity(
            latitude = extract safe latitude,
            longitude = extract safe longitude,
            address = extract safe address,
            idUser = idUser
    )

}