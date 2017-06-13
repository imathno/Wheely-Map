package aia.com.wheely_map.map;

import android.graphics.Bitmap;

import aia.com.wheely_map.user.User;

public class Ramp {

    private final String REGISTERED_BY;

    private String description;
    private Bitmap rampImage;
    private double latitude;
    private double longitude;

    public Ramp(User registeredBy, String description, Bitmap rampImage, double latitude, double longitude) {
        this.REGISTERED_BY = registeredBy;
        this.description = description;
        this.rampImage = rampImage;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Ramp(String userId, String description, Bitmap rampImage, double latitude, double longitude) {
        this.
    }

    public User getREGISTERED_BY() {
        return REGISTERED_BY;
    }

    public String getDescription() {
        return description;
    }

    public Bitmap getRampImage() {
        return rampImage;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
