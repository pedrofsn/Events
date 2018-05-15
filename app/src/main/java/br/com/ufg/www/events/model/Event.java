package br.com.ufg.www.events.model;

import android.content.Context;
import android.location.LocationManager;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Event {
    private double latitude;
    private double longitude;
    private String description;
    private static final String LOCAL_FORMATADO = "http://maps.googleapis.com/maps/api/geocode/xml?latlng=%f,%f";

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescricao(String description) {
        this.description = description;
    }

    public Event(double latitude, double longitude, String description) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
    }

    public Event() {
    }

    @Override
    public String toString() {
        return this.description +"  - local "+String.format(LOCAL_FORMATADO, this.latitude, this.longitude);
                //String.valueOf(this.latitude)+ ' '+String.valueOf(this.longitude);
    }
}
