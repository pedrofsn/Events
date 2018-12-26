package br.com.ufg.www.events.view.places.add_map

import android.annotation.SuppressLint
import android.location.Location
import android.os.Looper
import br.com.ufg.www.events.R
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds

abstract class BaseGoogleMapsActivityWithLocation : BaseGoogleMapsActivity() {

    val icon by lazy { BitmapDescriptorFactory.fromResource(R.drawable.ic_place_red_24dp) }

    private val fusedLocationClient by lazy { LocationServices.getFusedLocationProviderClient(this) }
    private val mLocationRequest = LocationRequest().apply {
        interval = 10000
        fastestInterval = 5000
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            val location = locationResult?.lastLocation
            location?.let { onLocationChange(it) }
        }
    }

    abstract val onLocationChange: (Location) -> Unit

    fun askForLocation() {
//        rxPermissions.request(
//                Manifest.permission.ACCESS_FINE_LOCATION,
//                Manifest.permission.ACCESS_COARSE_LOCATION)
//                .subscribe { granted: Boolean ->
//                    if (granted) {
        getLastKnowLocation()
//                    } else {
//                        showMessage(getString(R.string.precisamos_das_permissoes))
//                    }
//                }
    }

    @SuppressLint("MissingPermission")
    private fun getLastKnowLocation() {
        fusedLocationClient.requestLocationUpdates(mLocationRequest, locationCallback, Looper.getMainLooper())
//        ReactiveLocationProvider(this)
//                .lastKnownLocation
//                .subscribe { location: Location -> onLocationChange.invoke(location) }
    }


    fun seeMarkersOnCamera(positions: List<LatLng>) {
        mMap?.clear()

        if (positions.isNotEmpty()) {
            val builder = LatLngBounds.Builder()

            positions.forEach { builder.include(it) }

            val bounds = builder.build()
            val padding = 70 // offset from edges of the map in pixels
            val cu = CameraUpdateFactory.newLatLngBounds(bounds, padding)
            mMap?.animateCamera(cu)
        }
    }
}