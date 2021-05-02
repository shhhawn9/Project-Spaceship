package model;

import java.awt.geom.Rectangle2D;

public interface Ship extends Origin {
    public enum Direction {
        NONE(0, 0), UP(0, -1), DOWN(0, 1), LEFT(-1, 0), RIGHT(1, 0);
        // LUP(-1, -1), RUP(1, -1), LDOWN(-1, 1), RDOWN(1, 1);

        public final int dx;
        public final int dy;

        Direction(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }
    }

    public void setDirection(Direction d);

    public void setMovementBounds(Rectangle2D bounds);
}
