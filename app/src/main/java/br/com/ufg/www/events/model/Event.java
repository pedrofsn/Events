package br.com.ufg.www.events.model;

public class Event {
    private static final String LOCAL_FORMATADO = "http://maps.googleapis.com/maps/api/geocode/xml?latlng=%f,%f";
    private double latitude;
    private double longitude;
    private String description;

    public Event(double latitude, double longitude, String description) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
    }

    public Event() {
    }

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

    @Override
    public String toString() {
        return this.description + "  - local " + String.format(LOCAL_FORMATADO, this.latitude, this.longitude);
        //String.valueOf(this.latitude)+ ' '+String.valueOf(this.longitude);
    }
}
