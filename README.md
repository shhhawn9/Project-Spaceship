# My Personal Project

## Spaceship shooting game
A simple **Spaceship shooting game**. User will control a spaceship to survive in the asteroid group.
You can either use the weapon of your ship to destroy those asteroids or avoid them. Destroying asteroids can earn
points to upgrade spaceship's weapon and earn extra life.

Once I heard about we are going to work on a personal project, I started think about making a simple game.
I love play games, so I want to try to make a small game by myself. Spaceship shooting game is kind of well-knowing 
game. *I think if people interested in games they will play it*.


## User Stories

- As a user, I want to be able to move my ship.
- As a user, I want to be able to know my ship's location.
- As a user, I want to be able to see my health.
- As a user, I want the system to decide how many asteroid will be added to asteroid map.
- As a user, I want the system to remove asteroid which is out of bounds.
- As a user, I want to be able to save my scoreboard to a file.
- As a user, I want to be able to load scoreboard from a file.

## Phase 4: Task 2
Include a type hierarchy in the code.

Related classes:
- Origin
- Ship
- ShipImp
- Asteroid
- Fire

Related methods:
- void draw(Graphics2D g);
- void move();
- Shape getShape();
- boolean isIntersects(Origin other);

## Phase 4: Task 3
I think it is better to use abstract class instead of using interface for the Origin.
So that there will be less duplicated method in that type hierarchy relationship,
such as the getShape() method and isIntersects() method.

I think it is better to implement the Iterable<T> interface for AsteroidMap.
So that it can return an iterator over elements of Asteroid to GameComponent class,
and the collision checking can be done in GameComponent class.
This will make the map class simpler.