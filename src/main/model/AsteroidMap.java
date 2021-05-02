package model;

import java.awt.*;
import java.util.*;

public class AsteroidMap {
    private Set<Asteroid> map;

    // EFFECTS: Construct asteroid map with an empty ArrayList of asteroid.
    public AsteroidMap() {
        this.map = new HashSet<>();
    }

    // MODIFIES: this
    // EFFECTS: Add given asteroid to the map
    public void addAsteroids(Asteroid ast) {
        map.add(ast);
    }

    // EFFECTS: Return the size of map.
    public int getSize() {
        return map.size();
    }

    // EFFECTS: Draw each asteroid in the map.
    public void draw(Graphics2D g) {
        for (Asteroid a: map) {
            a.draw(g);
        }
    }

    // MODIFIES: this
    // EFFECTS: Move each asteroid and remove the one that is out of bounds.
    public void moveAsteroids() {
        for (Iterator<Asteroid> it = map.iterator(); it.hasNext();) {
            Asteroid a = it.next();

            if (!a.isVisible()) {
                it.remove();
            }
            a.move();
        }
    }

    // MODIFIES: this
    // EFFECTS: Return true and remove an asteroid if it hits the ship.
    public boolean checkAsteroidCollisions(Ship myShip) {
        for (Iterator<Asteroid> it = map.iterator(); it.hasNext();) {
            Asteroid a = it.next();

            if (myShip.isIntersects(a)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    // MODIFIES: this, fires
    // EFFECTS: Return true and remove both asteroid and fire, if an asteroid is shot by fire.
    public boolean checkAsteroidCollisions(Set<Fire> fires) {
        for (Iterator<Fire> itF = fires.iterator(); itF.hasNext();) {
            Fire f = itF.next();

            for (Iterator<Asteroid> itA = map.iterator(); itA.hasNext();) {
                Asteroid a = itA.next();

                if (a.isIntersects(f)) {
                    itA.remove();
                    itF.remove();
                    return true;
                }
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: Clear the set
    public void clear() {
        map.clear();
    }
}
