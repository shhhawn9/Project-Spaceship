package model;

import java.awt.*;

public interface Origin {
    public void draw(Graphics2D g);

    public void move();

    public Shape getShape();

    public boolean isIntersects(Origin other);
}
