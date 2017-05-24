package aia.com.wheely_map.utils;

public class Coordinates {

    public final String PLACE_NAME;
    public final float LONGITUDE;
    public final float LATITUDE;

    public Coordinates(final String placeName,
                       final float longitude, final float latitude) {
        this.PLACE_NAME = placeName;
        this.LONGITUDE = longitude;
        this.LATITUDE = latitude;
    }
}
