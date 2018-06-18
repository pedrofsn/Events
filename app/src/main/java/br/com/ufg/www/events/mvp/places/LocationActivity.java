package br.com.ufg.www.events.mvp.places;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import br.com.ufg.www.events.R;
import br.com.ufg.www.events.domain.BaseActivity;
import br.com.ufg.www.events.model.Place;

public class LocationActivity extends BaseActivity implements OnMapReadyCallback {

    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private static final int DEFAULT_ZOOM = 15;
    MapView mMapView;
    GoogleMap mGoogleMap;
    android.location.Location mLastKnownLocation;
    FusedLocationProviderClient mFusedLocationProviderClient;
    private boolean locationGranted;
    private Place place;
    private boolean hasPolyneDrawn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initMap();
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        place = getIntent().getParcelableExtra("place");
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    private void initMap() {
        mMapView = findViewById(R.id.mapView);
        mMapView.onCreate(getIntent().getExtras());
        mMapView.onResume(); // need to get the map to display immediately

        try {
            MapsInitializer.initialize(getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        getLocationPermission();
        getDeviceLocation();
    }

    private void getLocationPermission() {

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationGranted = true;
            updateLocationUI();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            if (locationGranted) {
                Task locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            // Set the map's camera position to the current location of the device.
                            mLastKnownLocation = (Location) task.getResult();
                            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(mLastKnownLocation.getLatitude(),
                                            mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));
                            updateLocationUI();
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        locationGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    locationGranted = true;
                }
            }
        }
        updateLocationUI();
    }

    private void updateLocationUI() {
        if (mMapView == null) {
            return;
        }

        try {
            if (mGoogleMap != null) {
                if (locationGranted) {
                    mGoogleMap.setMyLocationEnabled(true);
                    mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
                } else {
                    mGoogleMap.setMyLocationEnabled(false);
                    mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);
                    mLastKnownLocation = null;
                    getLocationPermission();
                }
            }
        } catch (SecurityException e) {
            Log.e("APP_ERROR", e.getMessage());
        }

        drawPolyne();
    }

    public void drawPolyne() {
        if (mLastKnownLocation != null && !hasPolyneDrawn) {
            LatLng lastLocation = new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude());

            PolylineOptions polylineOptions = new PolylineOptions();
            polylineOptions.visible(true);
            polylineOptions.color(Color.RED);
            polylineOptions.geodesic(true);

            polylineOptions.add(lastLocation);
            polylineOptions.add(place.toLatLng());

            mGoogleMap.addPolyline(polylineOptions);

            hasPolyneDrawn = true;

            addMarker();
        }
    }

    private void addMarker() {
        mGoogleMap.addMarker(new MarkerOptions().position(place.toLatLng()).title(place.getDescription()).flat(true));
    }
}
