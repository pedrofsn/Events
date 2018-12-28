package br.com.ufg.www.events.data.ui

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import br.com.redcode.base.extensions.extract
import br.com.redcode.base.mvvm.extensions.watchBR
import br.com.redcode.base.utils.Constants.EMPTY_STRING
import br.com.redcode.base.utils.Constants.SDF_BRAZILIAN_DATE_AND_TIME
import br.com.redcode.easyvalidation.isAllValid
import br.com.ufg.www.events.BR
import br.com.ufg.www.events.data.offline.entities.EventEntity
import br.com.ufg.www.events.data.offline.entities.PlaceEntity
import java.util.*

class InputEvent : BaseObservable() {

    var idEvent: Long? = null
    var idPlace: Long? = null

    val calendarStart = Calendar.getInstance()
    val calendarEnd = Calendar.getInstance()

    @get:Bindable
    var name: String = EMPTY_STRING

    @get:Bindable
    var dateStart by watchBR(BR.dateStart)

    @get:Bindable
    var dateEnd by watchBR(BR.dateEnd)

    @get:Bindable
    var latitude: String by watchBR(BR.latitude)

    @get:Bindable
    var longitude: String by watchBR(BR.longitude)

    @get:Bindable
    var address: String by watchBR(BR.address)

    var hasLocation = false
        @Bindable(value = ["latitude", "longitude", "address"])
        get() = isAllValid(latitude, longitude, address)

    fun refreshDates() {
        dateStart = SDF_BRAZILIAN_DATE_AND_TIME.format(calendarStart.time)
        dateEnd = SDF_BRAZILIAN_DATE_AND_TIME.format(calendarEnd.time)
    }

    fun toPlaceEntity(idUser: Long) = PlaceEntity(
            id = idPlace ?: 0,
            latitude = extract safe latitude,
            longitude = extract safe longitude,
            address = extract safe address,
            idUser = idUser
    )

    fun toEventEntity(idUser: Long, idPlace: Long) = EventEntity(
            id = idEvent ?: 0,
            idUser = idUser,
            idPlace = idPlace,
            dateStart = calendarStart.time,
            dateEnd = calendarEnd.time,
            name = extract safe name
    )

    fun isDateValid() = calendarEnd.timeInMillis > calendarStart.timeInMillis

}