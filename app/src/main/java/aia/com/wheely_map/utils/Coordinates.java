package aia.com.wheely_map.utils;

import java.util.HashMap;
import java.util.Map;

public class Coordinates {

    private static HashMap<String, Coordinates> wheelChairAccessibleLocation = new HashMap();

    public final String PLACE_NAME;
    public final float LONGITUDE;
    public final float LATITUDE;

    public Coordinates(final String PLACE_NAME,
                       final float LONGITUDE, final float LATITUDE) {
        this.PLACE_NAME = PLACE_NAME;
        this.LONGITUDE = LONGITUDE;
        this.LATITUDE = LATITUDE;

        wheelChairAccessibleLocation.put(PLACE_NAME, this);
    }

    public Map getWheelChairAccessibleLocation() {
        return wheelChairAccessibleLocation;
    }
}
