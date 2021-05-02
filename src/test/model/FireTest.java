package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.geom.Rectangle2D;

import static org.junit.jupiter.api.Assertions.*;

public class FireTest {
    Fire f1;
    Fire f2;

    @BeforeEach
    void runBefore() {
        f1 = new Fire(250, 250);
        f2 = new Fire(980, 250);
    }

    @Test
    void testMove() {
        f1.move();
        assertEquals(260, f1.getShape().getBounds().getX());
        assertEquals(249, f1.getShape().getBounds().getY());

        f2.move();
        assertEquals(990, f2.getShape().getBounds().getX());
        assertEquals(249, f2.getShape().getBounds().getY());
    }

    @Test
    void testIntersects() {
        Asteroid a1 = new Asteroid(259, 240, 20, 20, 10);
        Asteroid a2 = new Asteroid(260, 240, 20, 20, 10);

        assertTrue(f1.isIntersects(a1));

        assertFalse(f1.isIntersects(a2));
    }

    @Test
    void testIsVisible() {
        assertTrue(f2.isVisible());

        f2.move();
        assertTrue(f2.isVisible());

        f2.move();
        assertTrue(f2.isVisible());

        f2.move();
        assertFalse(f2.isVisible());
    }
}
