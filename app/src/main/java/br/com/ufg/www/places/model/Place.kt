package br.com.ufg.www.places.model

import br.com.ufg.www.places.App
import br.com.ufg.www.places.R

data class Place(
        val id: Long,
        val latitude: Double,
        val longitude: Double,
        val description: String?,
        val login: String
) {
    override fun toString() = "$description\n${App.instance.getString(R.string.latitude)}: $latitude\t${App.instance.getString(R.string.longitude)}: $longitude"
}