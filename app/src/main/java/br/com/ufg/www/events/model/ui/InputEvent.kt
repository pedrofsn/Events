package br.com.ufg.www.events.model.ui

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import br.com.redcode.base.mvvm.extensions.watchBR
import br.com.redcode.base.utils.Constants.EMPTY_STRING
import br.com.ufg.www.events.BR

data class InputEvent(
        val nothing: String = EMPTY_STRING
) : BaseObservable() {

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

}