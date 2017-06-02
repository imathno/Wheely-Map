package aia.com.wheely_map.user.map;

import org.junit.Before;
import org.junit.Test;

import aia.com.wheely_map.map.Ramp;
import aia.com.wheely_map.user.User;

import static junit.framework.Assert.assertEquals;

public class RampTest {

    private User testUser;

    @Before
    public void setUp() {
        testUser = new User("TEST", "TEST");
    }

    @Test
    public void getTitleTest() {
        String title = "WHOCANSWAGCANNOTSWAGNOMORESWAG";
        Ramp testRamp = new Ramp(testUser, title, -1, -1);

        assertEquals(title, testRamp.getTITLE());
    }

    @Test
    public void getLatAndLngTest() {
        double lat = Math.random() * 2000, lng = Math.random() * 2000;
        Ramp testRamp = new Ramp(testUser, "TEST", lat, lng);

        assertEquals(lat, testRamp.getLATITUDE());
        assertEquals(lng, testRamp.getLONGITUDE());
    }

    @Test
    public void getRegisteredByTest() {
        Ramp testRamp = new Ramp(testUser, "TEST", -1, -1);

        assertEquals(testRamp.getREGISTERED_BY(), testUser);
    }
}
