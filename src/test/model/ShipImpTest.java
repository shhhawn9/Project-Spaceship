package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static org.junit.jupiter.api.Assertions.*;

class ShipImpTest {
    ShipImp myShip;

    @BeforeEach
    void runBefore() {
        myShip = new ShipImp(250, 250);
        myShip.setMovementBounds(new Rectangle2D.Double(0, 0, 500, 500));
    }

    @Test
    void testShipImpl() {
        assertEquals(100, myShip.getHealth());
        assertEquals(270, myShip.getFirePointX());
        assertEquals(260, myShip.getFirePointY());

        Polygon polygon = new Polygon();
        assertEquals(polygon.getClass(), myShip.getShape().getClass());

        assertEquals(250, myShip.getShape().getBounds().getX());
        assertEquals(250, myShip.getShape().getBounds().getY());
    }

    @Test
    void testShipMoveUp() {
        myShip.setDirection(Ship.Direction.UP);

        myShip.move();
        assertEquals(250, myShip.getShape().getBounds().getX());
        assertEquals(246, myShip.getShape().getBounds().getY());

        myShip.move();
        assertEquals(250, myShip.getShape().getBounds().getX());
        assertEquals(242, myShip.getShape().getBounds().getY());
    }

    @Test
    void testShipMoveDown() {
        myShip.setDirection(Ship.Direction.DOWN);

        myShip.move();
        assertEquals(250, myShip.getShape().getBounds().getX());
        assertEquals(254, myShip.getShape().getBounds().getY());

        myShip.move();
        assertEquals(250, myShip.getShape().getBounds().getX());
        assertEquals(258, myShip.getShape().getBounds().getY());
    }

    @Test
    void testShipMoveLeft() {
        myShip.setDirection(Ship.Direction.LEFT);

        myShip.move();
        assertEquals(246, myShip.getShape().getBounds().getX());
        assertEquals(250, myShip.getShape().getBounds().getY());

        myShip.move();
        assertEquals(242, myShip.getShape().getBounds().getX());
        assertEquals(250, myShip.getShape().getBounds().getY());
    }

    @Test
    void testShipMoveRight() {
        myShip.setDirection(Ship.Direction.RIGHT);

        myShip.move();
        assertEquals(254, myShip.getShape().getBounds().getX());
        assertEquals(250, myShip.getShape().getBounds().getY());

        myShip.move();
        assertEquals(258, myShip.getShape().getBounds().getX());
        assertEquals(250, myShip.getShape().getBounds().getY());
    }

    @Test
    void testMoveOutOfBoundsUp() {
        ShipImp ship = new ShipImp(0, 0);
        ship.setMovementBounds(new Rectangle2D.Double(0, 0, 250, 250));
        ship.setDirection(Ship.Direction.UP);

        assertEquals(0, ship.getShape().getBounds().getX());
        assertEquals(0, ship.getShape().getBounds().getY());

        ship.move();
        assertEquals(0, ship.getShape().getBounds().getX());
        assertEquals(0, ship.getShape().getBounds().getY());
    }

    @Test
    void testMoveOutOfBoundsDown() {
        ShipImp ship = new ShipImp(0, 226);
        ship.setMovementBounds(new Rectangle2D.Double(0, 0, 250, 250));
        ship.setDirection(Ship.Direction.DOWN);

        assertEquals(246, ship.getShape().getBounds().getMaxY());

        ship.move();
        assertEquals(226, ship.getShape().getBounds().getY());
    }

    @Test
    void testMoveOutOfBoundsLeft() {
        ShipImp ship = new ShipImp(0, 0);
        ship.setMovementBounds(new Rectangle2D.Double(0, 0, 250, 250));
        ship.setDirection(Ship.Direction.LEFT);

        assertEquals(0, ship.getShape().getBounds().getX());

        ship.move();
        assertEquals(0, ship.getShape().getBounds().getX());
    }

    @Test
    void testMoveOutOfBoundsRight() {
        ShipImp ship = new ShipImp(226, 0);
        ship.setMovementBounds(new Rectangle2D.Double(0, 0, 250, 250));
        ship.setDirection(Ship.Direction.RIGHT);

        assertEquals(246, ship.getShape().getBounds().getMaxX());

        ship.move();
        assertEquals(226, ship.getShape().getBounds().getX());
    }

    @Test
    void testIntersects() {
        Asteroid a1 = new Asteroid(260, 260, 20, 20, 10);
        Asteroid a2 = new Asteroid(300, 300, 20, 20, 10);

        assertTrue(myShip.isIntersects(a1));
        assertEquals(80, myShip.getHealth());

        assertFalse(myShip.isIntersects(a2));
        assertEquals(80, myShip.getHealth());
    }
}