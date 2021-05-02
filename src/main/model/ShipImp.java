package model;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

public class ShipImp implements Ship {
    private static final Color FILL = Color.GREEN;
    private static final Color BORDER = Color.BLACK;
    private static final int HEIGHT = 20;
    private static final int WIDTH = HEIGHT;
    private static final int HEALTH_PRE_HIT = 20;

    private final Polygon shape;
    private int health;
    private Direction direction;
    private Rectangle2D movementBounds;

    // EFFECTS: Construct ShipImpl with given coordinates, set the health, and direction.
    public ShipImp(int x, int y) {
        this.shape = new Polygon(
                new int[] {0, 0, WIDTH}, // X of top left, bottom left, front middle
                new int[] {0, HEIGHT, HEIGHT / 2}, 3); // Y of top left, bottom left, front middle
        shape.translate(x, y);

        this.health = 100;

        direction = Direction.NONE;
    }

    @Override
    // MODIFIES: this
    // EFFECTS: Set direction to be the given direction.
    public void setDirection(Direction d) {
        this.direction = d;
    }

    @Override
    // MODIFIES: this
    // EFFECTS: Set the movement bounds.
    public void setMovementBounds(Rectangle2D bounds) {
        this.movementBounds = bounds;
    }

    @Override
    // EFFECTS: Draw the ship.
    public void draw(Graphics2D g) {
        g.setColor(BORDER);
        g.draw(shape);
        g.setColor(FILL);
        g.fill(shape);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: Move the ship along the direction.
    public void move() {
        shape.translate(direction.dx * 4, direction.dy * 4);
        outOfBoundsMovement();
    }

    @Override
    // EFFECTS: Return the shape of the ship.
    public Shape getShape() {
        return shape;
    }

    @Override
    // EFFECTS: Return true if the ship hit the asteroid, false otherwise.
    public boolean isIntersects(Origin other) {
        Area otherArea = new Area(other.getShape());
        Area shipArea = new Area(shape);

        // Sets the shape of this Area to the intersection of its current shape and the shape of the specified Area
        shipArea.intersect(otherArea);

        // Test whether this Area object encloses any area
        if (shipArea.isEmpty()) {
            return false;
        } else {
            health -= HEALTH_PRE_HIT;
            return true;
        }
    }

    // MODIFIES: this
    // EFFECTS: Helper method of controlling boundary issues.
    private void outOfBoundsMovement() {
        // Top and down direction
        if (shape.getBounds().getY() < movementBounds.getY()) {
            shape.translate(0, 4);
        } else if (shape.getBounds().getMaxY() >= movementBounds.getMaxY()) {
            shape.translate(0, -4);
        }

        // Left a right direction
        if (shape.getBounds().getX() < movementBounds.getX()) {
            shape.translate(4, 0);
        } else if (shape.getBounds().getMaxX() >= movementBounds.getMaxX()) {
            shape.translate(-4, 0);
        }
    }

    // EFFECTS: Return health of the ship.
    public int getHealth() {
        return health;
    }

    // EFFECTS: Return X coordinate of fire point of the ship.
    public int getFirePointX() {
        return (int) shape.getBounds().getX() + WIDTH;
    }

    // EFFECTS: Return Y coordinate of fire point of the ship.
    public int getFirePointY() {
        return (int) shape.getBounds().getY() + HEIGHT / 2;
    }
}
