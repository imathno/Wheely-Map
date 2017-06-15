package aia.com.wheely_map.map;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import aia.com.wheely_map.user.UserManager;

public abstract class RampManager {

    private static final String TAG = RampManager.class.getSimpleName();

    private static List<Ramp> registeredRamps;

    public static boolean registerRamp(String description, double latitude, double longitude) {
        if (registeredRamps == null) {
            registeredRamps = new ArrayList<>();
        }

        if (findRamp(longitude, latitude) == null) {
            Log.d(TAG, "registerRamp:Creating New Ramp");
            Ramp newRamp = new Ramp(UserManager.getLoggedInUser(), description, null, longitude, latitude);
            registeredRamps.add(newRamp);
            return true;
        }
        return false;
    }

    private void storeRamp() {

    }

    public static Ramp findRamp(double latitude, double longitude) {
        for (Ramp ramp : registeredRamps) {
            if (ramp.getLatitude() == latitude && ramp.getLongitude() == longitude) {
                return ramp;
            }
        }
        return null;
    }

    public synchronized static List<Ramp> getRegisteredRamps() {
        return registeredRamps;
    }
}
