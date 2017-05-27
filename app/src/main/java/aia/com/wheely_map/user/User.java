package aia.com.wheely_map.user;

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

    public void setUserPoints(long points) {
        this.userPoints = points;
    }

    public boolean addUserPoints(long points) {
        if (points < 0) {
            negativePointsError(points);
            return false;
        }
        this.userPoints += points;
        return true;
    }

    public boolean removeUserPoints(long points) {
        if (points < 0 || userPoints - points < 0) {
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

    public long getUserPoints() {
        return userPoints;
    }

    private static void negativePointsError(long points) {
        Log.e(TAG, "Attempted to use negative points " + points);
    }
}
