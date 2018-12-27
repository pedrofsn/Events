package br.com.ufg.www.events.data.ui

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import br.com.redcode.base.extensions.extract
import br.com.redcode.base.mvvm.extensions.watchBR
import br.com.redcode.base.utils.Constants.EMPTY_STRING
import br.com.redcode.base.utils.Constants.SDF_BRAZILIAN_DATE
import br.com.redcode.easyvalidation.isAllValid
import br.com.ufg.www.events.BR
import br.com.ufg.www.events.data.offline.entities.EventEntity
import br.com.ufg.www.events.data.offline.entities.PlaceEntity

class InputEvent : BaseObservable() {

    @get:Bindable
    var name: String = EMPTY_STRING

    @get:Bindable
    var date: String = EMPTY_STRING

    @get:Bindable
    var latitude: String by watchBR(BR.latitude)

    @get:Bindable
    var longitude: String by watchBR(BR.longitude)

    @get:Bindable
    var address: String by watchBR(BR.address)

    var hasLocation = false
        @Bindable(value = ["latitude", "longitude", "address"])
        get() = isAllValid(latitude, longitude, address)

    fun toDate() = SDF_BRAZILIAN_DATE.parse(extract safe date)

    fun toPlaceEntity(idUser: Long) = PlaceEntity(
            latitude = extract safe latitude,
            longitude = extract safe longitude,
            address = extract safe address,
            idUser = idUser
    )

    fun toEventEntity(idUser: Long, idPlace: Long) = EventEntity(
            idUser = idUser,
            idPlace = idPlace,
            date = toDate(),
            name = extract safe name
    )

}