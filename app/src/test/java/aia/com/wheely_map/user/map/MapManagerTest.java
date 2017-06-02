package aia.com.wheely_map.user.map;

import org.junit.Before;
import org.junit.Test;

import aia.com.wheely_map.map.MapManager;
import aia.com.wheely_map.map.Ramp;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class MapManagerTest {

    @Before
    public void setUp() {
    }

    @Test
    public void registerRampTest() {
        MapManager.registerRamp("TEST", -1, -1);

        Ramp findThisRamp = MapManager.findRamp(-1 , -1);
        assertTrue(findThisRamp != null);
        MapManager.getRegisteredRamps().remove(findThisRamp);
    }

    @Test
    public void findRampTest() {
        MapManager.registerRamp("TEST", -1, -1);

        assertFalse(MapManager.findRamp(-1, -2) != null);
        assertTrue(MapManager.findRamp(-1, -1) != null);

        MapManager.getRegisteredRamps().remove(MapManager.findRamp(-1, -1));
    }
}
