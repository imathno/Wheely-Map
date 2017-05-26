package aia.com.wheely_map;

import android.util.Log;

public class User {

    private static final String TAG = User.class.getSimpleName();

    private String username;
    private String password;

    private long userPoints;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.userPoints = 0;
    }

    public void addPoints(long points) {
        if (points < 0) {
            negativePointsError(points);
        }
        this.userPoints += points;
    }

    public boolean removePoints(long points) {
        if (points < 0) {
            negativePointsError(points);
            return false;
        }
        this.userPoints -= points;
        return true;
    }

    public String getUsername() {
        return username;
    }

    @Deprecated
    public String getPassword() {
        return password;
    }

    private static void negativePointsError(long points) {
        Log.e(TAG, "Attempted to use negative points " + points);
    }
}
