package br.com.ufg.www.events.model

data class Event(
        val latitude: Double,
        val longitude: Double,
        val description: String?
) {
    override fun toString() = "$description - local http://maps.googleapis.com/maps/api/geocode/xml?latlng=$latitude,$longitude"
}