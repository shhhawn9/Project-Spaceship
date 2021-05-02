package ui;

import model.Ship;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ShipKeyListener extends KeyAdapter {
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_KP_UP:
            case KeyEvent.VK_UP:
                GameComponent.myShip.setDirection(Ship.Direction.UP);
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_KP_DOWN:
            case KeyEvent.VK_DOWN:
                GameComponent.myShip.setDirection(Ship.Direction.DOWN);
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_KP_LEFT:
            case KeyEvent.VK_LEFT:
                GameComponent.myShip.setDirection(Ship.Direction.LEFT);
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_KP_RIGHT:
            case KeyEvent.VK_RIGHT:
                GameComponent.myShip.setDirection(Ship.Direction.RIGHT);
                break;
            default:
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_KP_UP:
            case KeyEvent.VK_UP:
            case KeyEvent.VK_S:
            case KeyEvent.VK_KP_DOWN:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_A:
            case KeyEvent.VK_KP_LEFT:
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_D:
            case KeyEvent.VK_KP_RIGHT:
            case KeyEvent.VK_RIGHT:
                GameComponent.myShip.setDirection(Ship.Direction.NONE);
                break;
            default:
        }
    }
}
