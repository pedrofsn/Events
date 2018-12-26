package br.com.ufg.www.events.extensions

import android.location.Location
import com.google.android.gms.maps.model.LatLng

/**
 * Created by pedrofsn on 17/10/2017.
 */

fun LatLng.isSameLocation(other: LatLng) = latitude == other.latitude && longitude == other.longitude

fun Location.toLatLng() = LatLng(latitude, longitude)