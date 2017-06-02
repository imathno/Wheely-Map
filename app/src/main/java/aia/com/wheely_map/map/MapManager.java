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
    private static List<Ramp> toAddMarker = new ArrayList<>();

    public static boolean registerRamp(String title, double latitude, double longitude) {
        if (findRamp(longitude, latitude) == null && gMap != null) {
            Ramp newRamp = new Ramp(UserManager.getLoggedInUser(), title, longitude, latitude);
            registeredRamps.add(newRamp);
            addMarker(newRamp);
            return true;
        }
        return false;
    }

    public static void addMarker(Ramp ramp) {
        toAddMarker.add(ramp);
    }

    public static List<Ramp> getToAddMarkerList() {
        return toAddMarker;
    }

    public static Ramp findRamp(double latitude, double longitude) {
        for (Ramp ramp : registeredRamps) {
            if (ramp.getLATITUDE() == latitude && ramp.getLONGITUDE() == longitude) {
                return ramp;
            }
        }
        return null;
    }
}
