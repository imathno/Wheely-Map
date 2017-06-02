package aia.com.wheely_map.user.user;

import org.junit.Before;
import org.junit.Test;

import aia.com.wheely_map.user.User;
import aia.com.wheely_map.user.UserManager;

import static junit.framework.Assert.assertTrue;

public class UserManagerTest {

    private User testUser;

    @Before
    public void setUp() {
        testUser = new User("test", "test123");
    }

    @Test
    public void registerUserTest() {
        UserManager.registerUser("TEST", "TEST");
        User test = UserManager.findUser("TEST");
        assertTrue(test != null);
    }

    @Test
    public void addUserTest() {
        if (UserManager.getRegisteredUserList().contains(testUser)) {
            UserManager.removeUser(testUser);
        }
        assertTrue(UserManager.addUser(testUser));
    }

    @Test
    public void removeUserTest() {
        if (!UserManager.getRegisteredUserList().contains(testUser)) {
            UserManager.addUser(testUser);
        }
        assertTrue(UserManager.removeUser(testUser));
    }

    @Test
    public void findUserTest() {
        final User invalidUser = new User("Billy", "488");
        UserManager.addUser(testUser);
        assertTrue(UserManager.findUser(invalidUser.getUsername()) == null);
        assertTrue(UserManager.findUser(testUser.getUsername()) != null);
    }
}
