package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class AsteroidMapTest {
    AsteroidMap map;
    Asteroid a1;
    Asteroid a2;

    @BeforeEach
    void runBefore() {
        map = new AsteroidMap();
        a1 = new Asteroid(250, 250, 20, 20, 10);
        a2 = new Asteroid(50, 50, 10, 10, 10);
    }

    @Test
    void testAddAsteroid() {
        assertEquals(0, map.getSize());

        map.addAsteroids(a1);
        assertEquals(1, map.getSize());

        map.addAsteroids(a2);
        assertEquals(2, map.getSize());
    }

    @Test
    void testMoveAsteroids() {
        map.addAsteroids(a1);
        map.addAsteroids(a2);

        map.moveAsteroids();
        assertEquals(2, map.getSize());

        map.moveAsteroids();
        assertEquals(2, map.getSize());

        map.moveAsteroids();
        map.moveAsteroids();
        map.moveAsteroids();
        map.moveAsteroids();
        map.moveAsteroids();
        map.moveAsteroids();
        assertEquals(1, map.getSize());
    }

    @Test
    void testCheckAsteroidShipCollisions() {
        Ship ship = new ShipImp(250, 250);

        map.addAsteroids(a1);
        map.addAsteroids(a2);
        assertTrue(map.checkAsteroidCollisions(ship));
        assertEquals(1, map.getSize());

        assertFalse(map.checkAsteroidCollisions(ship));
        assertEquals(1, map.getSize());
    }

    @Test
    void testCheckAsteroidFireCollisions() {
        Set<Fire> fires = new HashSet<>();
        fires.add(new Fire(240, 260));
        fires.add(new Fire(241, 260));

        map.addAsteroids(a1);
        map.addAsteroids(a2);
        assertTrue(map.checkAsteroidCollisions(fires));
        assertEquals(1, map.getSize());
        assertEquals(1, fires.size());

        assertFalse(map.checkAsteroidCollisions(fires));
    }

    @Test
    void testClear() {
        map.addAsteroids(a1);
        map.addAsteroids(a2);
        assertEquals(2, map.getSize());

        map.clear();
        assertEquals(0, map.getSize());
    }

    @Test
    void testWithFactory() {
        map.addAsteroids(Factory.getInstance().makeAsteroid());
        map.addAsteroids(Factory.getInstance().makeAsteroid());
        map.addAsteroids(Factory.getInstance().makeAsteroid());

        assertEquals(3, map.getSize());
    }
}
