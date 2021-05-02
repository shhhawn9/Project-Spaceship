package model;

import ui.GameFrame;

import java.awt.*;
import java.awt.geom.Area;

public class Fire implements Origin {
    private static final Color FILL = Color.YELLOW;

    private final Polygon shape;
    private final int velocity = 10;

    // EFFECTS: Construct fire with given coordinates.
    public Fire(int x, int y) {
        this.shape = new Polygon(
                new int[] {0, 10, 10, 0},
                new int[] {-1, -1, 1, 1}, 4);
        shape.translate(x, y);
    }

    @Override
    // EFFECTS: Draw the fire.
    public void draw(Graphics2D g) {
        g.setColor(FILL);
        g.fill(shape);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: Move the fire form ship to the right side of the frame.
    public void move() {
        shape.translate(velocity, 0);
    }

    @Override
    // EFFECTS: Return the shape of the fire.
    public Shape getShape() {
        return shape;
    }

    @Override
    // EFFECTS: Return true if the fire hit any asteroid, false otherwise.
    public boolean isIntersects(Origin other) {
        Area otherArea = new Area(other.getShape());
        Area fireArea = new Area(shape);

        fireArea.intersect(otherArea);

        return !fireArea.isEmpty();
    }

    // EFFECTS: Return true if fire is in the play field.
    public boolean isVisible() {
        return shape.getBounds().getX() <= GameFrame.WIDTH;
    }
}
