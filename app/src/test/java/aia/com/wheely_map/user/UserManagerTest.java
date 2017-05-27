package aia.com.wheely_map.user;


import android.test.mock.MockContext;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;

public class UserManagerTest {

    private User testUser;

    @Before
    public void setUp() {
        testUser = new User("test", "test123");
    }

    @Test
    public void addUserTest() {
        UserManager.addUser(testUser);
        assertTrue(UserManager.getRegisteredUserList().contains(testUser));
    }

    @Test
    public void removeUserTest() {
        if (!UserManager.getRegisteredUserList().contains(testUser)) {
            UserManager.addUser(testUser);
        }
        UserManager.removeUser(testUser);
        assertTrue(!UserManager.getRegisteredUserList().contains(testUser));
    }
}
