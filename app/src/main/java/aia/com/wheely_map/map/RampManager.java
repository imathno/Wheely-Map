package aia.com.wheely_map.map;

import java.util.ArrayList;
import java.util.List;

import aia.com.wheely_map.user.UserManager;

public abstract class RampManager {

    private static final String TAG = RampManager.class.getSimpleName();

    private static List<Ramp> registeredRamps;
    private static List<Ramp> toAddMarker;

    public static boolean registerRamp(String description, double latitude, double longitude) {
        if (registeredRamps == null) {
            registeredRamps = new ArrayList<>();
        }

        if (findRamp(longitude, latitude) == null) {
            Ramp newRamp = new Ramp(UserManager.getLoggedInUser(), description, null, longitude, latitude);
            registeredRamps.add(newRamp);
            addMarker(newRamp);
            return true;
        }
        return false;
    }

    public static void addMarker(Ramp ramp) {
        if (toAddMarker == null) {
            toAddMarker = new ArrayList<>();
        }
        toAddMarker.add(ramp);
    }

    public static Ramp findRamp(double latitude, double longitude) {
        for (Ramp ramp : registeredRamps) {
            if (ramp.getLatitude() == latitude && ramp.getLongitude() == longitude) {
                return ramp;
            }
        }
        return null;
    }

    public static List<Ramp> getRegisteredRamps() {
        return registeredRamps;
    }

    public static List<Ramp> getToAddMarkerList() {
        return toAddMarker;
    }
}
