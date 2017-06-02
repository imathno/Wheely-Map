package aia.com.wheely_map.user;

public abstract class UserManager {

    private static final String TAG = UserManager.class.getSimpleName();

    private static User loggedInUser;

    public static void setLoggedInUser(User user) {
        loggedInUser = user;
    }

    public static void logOutUser() {
        loggedInUser = null;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }
}
