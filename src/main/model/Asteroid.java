package model;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Asteroid implements Origin {
    private static final Color FILL = Color.DARK_GRAY;
    private static final Color BORDER = Color.LIGHT_GRAY;

    private final Ellipse2D.Double shape;
    private final int velocity;

    // EFFECTS: Construct asteroid with given coordinates.
    public Asteroid(int x, int y, int width, int height, int velocity) {
        shape = new Ellipse2D.Double(x, y, width, height);
        this.velocity = velocity;
    }

    @Override
    // EFFECTS: Draw the asteroid.
    public void draw(Graphics2D g) {
        g.setColor(BORDER);
        g.draw(shape);
        g.setColor(FILL);
        g.fill(shape);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: Move the asteroid form right to left side of the frame
    public void move() {
        shape.x -= velocity;
    }

    @Override
    // EFFECTS: Return the shape of the asteroid.
    public Shape getShape() {
        return shape;
    }

    @Override
    // EFFECTS: Return true if the asteroid hit any other Origin object, false otherwise.
    public boolean isIntersects(Origin other) {
        Area otherArea = new Area(other.getShape());
        Area asteroidArea = new Area(shape);

        asteroidArea.intersect(otherArea);

        return !asteroidArea.isEmpty();
    }

    // EFFECTS: Return true if asteroid is in the play field.
    public boolean isVisible() {
        return shape.x + shape.width >= 0;
    }
}
