package aia.com.wheely_map.map;

import android.util.Log;

import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;
import java.util.List;

import aia.com.wheely_map.user.UserManager;

public abstract class MapManager {

    private static final String TAG = MapManager.class.getSimpleName();

    private static GoogleMap gMap;

    private static List<Ramp> registeredRamps = new ArrayList<>();

    public static boolean registerRamp(String title, double latitude, double longitude) {
        if (findRamp(longitude, latitude) == null && gMap != null) {
            Ramp newRamp = new Ramp(UserManager.getLoggedInUser(), title, longitude, latitude);
            newRamp.placeMarker();
            registeredRamps.add(newRamp);
            return true;
        }
        return false;
    }

    public static Ramp findRamp(double latitude, double longitude) {
        for (Ramp ramp : registeredRamps) {
            if (ramp.getLATITUDE() == latitude && ramp.getLONGITUDE() == longitude) {
                return ramp;
            }
        }
        return null;
    }

    public static void setGoogleMap(GoogleMap googleMap) {
        gMap = googleMap;
    }

    public static GoogleMap getGoogleMap() {
        if (gMap == null) {
            Log.e(TAG, "Map isn't set");
            return null;
        }
        return gMap;
    }
}
