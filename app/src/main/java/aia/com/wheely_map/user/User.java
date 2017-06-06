package aia.com.wheely_map.user;

import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.plus.model.people.Person;

public class User {

    private static final String TAG = User.class.getSimpleName();

    private GoogleSignInAccount user;

    private String username;
    private final String USER_ID;

    private long userPoints;

    public User(GoogleSignInAccount user) {
        this.user = user;
        username = user.getDisplayName();
        USER_ID = user.getId();
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

    public long getUserPoints() {
        return userPoints;
    }

    private static void negativePointsError(long points) {
        Log.e(TAG, "Attempted to use negative points " + points);
    }
}
