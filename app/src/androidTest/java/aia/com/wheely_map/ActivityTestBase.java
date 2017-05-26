package aia.com.wheely_map;

import org.junit.Before;

import static junit.framework.Assert.assertEquals;

public abstract class ActivityTestBase {

    @Before
    public void setUp() {

    }

    public void stringEquals(String expected, String actual) {
        assertEquals(expected, actual);
    }
}
