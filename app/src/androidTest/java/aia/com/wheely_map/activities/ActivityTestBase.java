package aia.com.wheely_map.activities;

import org.junit.Before;

import aia.com.wheely_map.user.User;

import static junit.framework.Assert.assertEquals;

public abstract class ActivityTestBase {

    protected User testUser;

    @Before
    public void setUp() {
        testUser = new User("test", "test1234");
    }

    public void stringEquals(String expected, String actual) {
        assertEquals(expected, actual);
    }
}
