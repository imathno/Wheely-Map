package aia.com.wheely_map.map;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import aia.com.wheely_map.user.User;

public class Ramp {

    public final User REGISTERED_BY;

    private final String TITLE;
    private final double LATITUDE;
    private final double LONGITUDE;

    public Ramp(User registeredBy, String title, double latitude, double longitude) {
        this.REGISTERED_BY = registeredBy;
        this.TITLE = title;
        this.LATITUDE = latitude;
        this.LONGITUDE = longitude;
    }

    public boolean placeMarker() {
        GoogleMap map = MapManager.getGoogleMap();
        if (map != null) {
            MapManager.getGoogleMap().addMarker(new MarkerOptions()
                    .position(new LatLng(LATITUDE, LONGITUDE))
                    .title(TITLE));
            return true;
        }
        return false;
    }

    public String getTITLE() {
        return TITLE;
    }

    public double getLATITUDE() {
        return LATITUDE;
    }

    public double getLONGITUDE() {
        return LONGITUDE;
    }
}
