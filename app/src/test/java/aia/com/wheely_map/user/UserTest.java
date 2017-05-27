package aia.com.wheely_map.user;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class UserTest {

    private User testUser;

    @Before
    public void setUp() {
        testUser = new User("TEST", "TEST");
    }

    @Test
    public void addUserPointsTest() {
        testUser.setUserPoints(0);
        assertTrue(testUser.addUserPoints(1));
        assertTrue(testUser.getUserPoints() == 1);
        testUser.setUserPoints(0);
        assertFalse(testUser.addUserPoints(-1));
        assertTrue(testUser.getUserPoints() == 0);
    }

    @Test
    public void removeUserPointsTest() {
        testUser.setUserPoints(1000);
        assertTrue(testUser.removeUserPoints(1));
        assertTrue(testUser.getUserPoints() == 999);
        testUser.setUserPoints(1000);
        assertFalse(testUser.removeUserPoints(-1));
        assertTrue(testUser.getUserPoints() == 1000);
    }
}
