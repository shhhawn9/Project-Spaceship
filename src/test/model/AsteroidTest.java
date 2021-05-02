package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.geom.Rectangle2D;

import static org.junit.jupiter.api.Assertions.*;

public class AsteroidTest {
    Asteroid a1;
    Asteroid a2;

    @BeforeEach
    void runBefore() {
        a1 = new Asteroid(250, 250, 10, 10, 10);
        a2 = new Asteroid(500, 250, 10, 10, 5);
    }

    @Test
    void testMove() {
        a1.move();
        assertEquals(240, a1.getShape().getBounds().getX());
        assertEquals(250, a1.getShape().getBounds().getY());

        a2.move();
        assertEquals(495, a2.getShape().getBounds().getX());
        assertEquals(250, a2.getShape().getBounds().getY());
    }

    @Test
    void testIntersects() {
        Ship ship = new ShipImp(250, 250);
        ship.setMovementBounds(new Rectangle2D.Double(0, 0, 250, 250));

        assertTrue(a1.isIntersects(ship));

        assertFalse(a2.isIntersects(ship));
    }

    @Test
    void testIsVisible() {
        Asteroid a = new Asteroid(10, 250, 10, 10, 10);

        assertTrue(a.isVisible());

        a.move();
        assertTrue(a.isVisible());

        a.move();
        assertTrue(a.isVisible());

        a.move();
        assertFalse(a.isVisible());
    }
}
