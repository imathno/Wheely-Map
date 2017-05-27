package aia.com.wheely_map.user;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public abstract class UserManager {

    private static final String TAG = UserManager.class.getSimpleName();

    private static List<User> registeredUserList = new ArrayList<>();

    private static User loggedInUser;

    public static void registerUser(String username, String password) {
        User newUser = new User(username, password);
        registeredUserList.add(newUser);
    }

    public static boolean loginUser(User user) {
        if (loggedInUser != null) {
            Log.e(TAG, "Already logged in");
            return false;
        } else if (user == null) {
            Log.e(TAG, "User is null");
            return false;
        }
        loggedInUser = user;
        return loggedInUser != null;
    }

    public static User findUser(String username) {
        for (User user : registeredUserList) {
            if (user.getUsername().equals(username))
                return user;
        }
        return null;
    }

    public static boolean addUser(User user) {
        if (registeredUserList.contains(user)) {
            return false;
        }
        registeredUserList.add(user);
        Log.i(TAG, user.getUsername() + " added");
        return true;
    }

    public static boolean removeUser(User user) {
        if (registeredUserList.contains(user)) {
            registeredUserList.remove(user);
            Log.i(TAG, user.getUsername() + " removed");
            return true;
        }
        return false;
    }

    public static List<User> getRegisteredUserList() {
        return registeredUserList;
    }
}
