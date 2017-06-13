package aia.com.wheely_map.user.map;

import org.junit.Before;
import org.junit.Test;

import aia.com.wheely_map.map.RampManager;
import aia.com.wheely_map.map.Ramp;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class MapManagerTest {

    @Before
    public void setUp() {
    }

    @Test
    public void registerRampTest() {
        RampManager.registerRamp("TEST", -1, -1);

        Ramp findThisRamp = RampManager.findRamp(-1 , -1);
        assertTrue(findThisRamp != null);
        RampManager.getRegisteredRamps().remove(findThisRamp);
    }

    @Test
    public void findRampTest() {
        RampManager.registerRamp("TEST", -1, -1);

        assertFalse(RampManager.findRamp(-1, -2) != null);
        assertTrue(RampManager.findRamp(-1, -1) != null);

        RampManager.getRegisteredRamps().remove(RampManager.findRamp(-1, -1));
    }
}
