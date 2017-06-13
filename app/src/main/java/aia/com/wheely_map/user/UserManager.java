package aia.com.wheely_map.user;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public abstract class UserManager {

    private static final String TAG = UserManager.class.getSimpleName();

    private static List<User> registeredUsers = new ArrayList<>();

    private static User loggedInUser;

    public static void setLoggedInUser(User user) {
        loggedInUser = user;
    }

    public static void logOutUser() {
        loggedInUser = null;
    }

    public void addUserToRegisteredUsers(User user) {
        registeredUsers.add(user);
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static User findUserByID(String userId) {
        for (User user : registeredUsers)
            if (user.getUSER_ID().equals(userId))
                return user;
        Log.i(TAG, "findUserByID:" + userId + " Not Found");
        return null;
    }

    public static void enableTestUser() {
        User testUser = new User();
        registeredUsers.add(testUser);
    }
}
