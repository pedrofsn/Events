package br.com.ufg.www.events.view.places.add_map

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Geocoder
import androidx.core.app.ActivityCompat
import br.com.redcode.base.activities.BaseActivity
import br.com.redcode.base.utils.Constants.EMPTY_STRING
import br.com.redcode.easyvalidation.isNullOrEmpty
import br.com.ufg.www.events.R
import br.com.ufg.www.events.extensions.isSameLocation
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import java.io.IOException
import java.util.*

abstract class BaseGoogleMapsActivity : BaseActivity(), OnMapReadyCallback, GoogleMap.OnCameraIdleListener {

    companion object {
        const val ZOOM_OUT = 3
        const val ZOOM_IN = 15
        const val CHANGE_LOCATION = 1992
    }

    override val layout = R.layout.activity_google_maps

    private val mapFragment by lazy { supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment }

    open var latLng: LatLng? = null
    open var addressString: String = EMPTY_STRING
    var mMap: GoogleMap? = null
    private val defaultPosition by lazy {
        if (intent?.extras?.getParcelable<LatLng>("latLng") != null) intent?.extras?.getParcelable<LatLng>("latLng") else LatLng(-16.348260, -49.372287)
    }

    private val hasPermission by lazy {
        ActivityCompat.checkSelfPermission(this@BaseGoogleMapsActivity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this@BaseGoogleMapsActivity, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    override fun afterOnCreate() {
        enableHomeAsUpActionBar()
        mapFragment.getMapAsync(this)
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap!!.apply {
            uiSettings.apply {
                setAllGesturesEnabled(true)
                isCompassEnabled = true
                isZoomControlsEnabled = true
                isMyLocationButtonEnabled = hasPermission
            }

            isMyLocationEnabled = hasPermission
            setOnCameraIdleListener(this@BaseGoogleMapsActivity)
        }

        setDefaultPosition()
    }

    override fun onCameraIdle() {
        val latLngSelectedByUser = mMap!!.cameraPosition.target

        if (latLng == null || latLng?.isSameLocation(latLngSelectedByUser) == false) {
            latLng = latLngSelectedByUser
            mMap!!.clear()
            runBlocking { updateAdressString() }
        }
    }

    private fun setDefaultPosition() = setPosition(position = defaultPosition, zoom = ZOOM_OUT)

    open fun setPosition(position: LatLng?, zoom: Int? = null) {
        if (mMap != null && position != null) {
            val cameraPosition = CameraPosition.Builder()
                    .target(position)

            zoom?.let { cameraPosition.zoom(it.toFloat()) }

            mMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition.build()))
        }
    }

    private suspend fun updateAdressString() {
        coroutineScope {
            addressString = EMPTY_STRING
            latLng.apply {
                val result = StringBuilder()
                try {
                    val geocoder = Geocoder(this@BaseGoogleMapsActivity, Locale.getDefault())
                    val addresses = geocoder.getFromLocation(latLng!!.latitude, latLng!!.longitude, 1)
                    if (addresses.isNotEmpty()) {
                        val address = addresses[0]

                        for (i in 0..address.maxAddressLineIndex) {
                            if (i == address.maxAddressLineIndex) {
                                result.append(address.getAddressLine(i))
                            } else {
                                val line = address.getAddressLine(i) + ", "
                                result.append(line)
                            }
                        }

                        if (!isNullOrEmpty(result.toString())) {
//                            uiThread {
                            addressString = result.toString()
                            onAdressStringUpdated(addressString)
//                            }
                        }
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

    open fun onAdressStringUpdated(addressString: String) {

    }

}
