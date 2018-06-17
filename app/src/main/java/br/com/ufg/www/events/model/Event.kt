package br.com.ufg.www.events.model

import br.com.ufg.www.events.App
import br.com.ufg.www.events.R

data class Event(
        val id: Long,
        val latitude: Double,
        val longitude: Double,
        val description: String?,
        val login: String
) {
    override fun toString() = "$description\n${App.instance.getString(R.string.latitude)}: $latitude\t${App.instance.getString(R.string.longitude)}: $longitude"
}