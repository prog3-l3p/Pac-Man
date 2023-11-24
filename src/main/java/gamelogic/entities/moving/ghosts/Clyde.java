package gamelogic.entities.moving.ghosts;

import utility.ResourceHandler;
import utility.ShortestPathFinder;

import java.awt.*;
import java.awt.image.BufferedImage;
/**
 * This class represents Clyde
 */
public class Clyde extends Ghost {
    /**
     * Constructor for Clyde
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public Clyde(int x, int y) {
        super(x, y);
        setSprite(RIGHT_1);
    }

    /**
     * Gets the sprite for Clyde
     * @return current sprite
     */
    @Override
    public BufferedImage getSprite() {
        getNextAnimation();
        // If Clyde is frightened, return the frightened sprite
        if (isFrightened) {
            return ResourceHandler.getSpriteMap("frightened").get(spriteName);
        // If Clyde is dead, return the dead sprite
        } else if (isDead) {
            return ResourceHandler.getSpriteMap("dead").get(spriteName);
        // Otherwise, return the normal sprite
        } else {
            return ResourceHandler.getSpriteMap("clyde").get(spriteName);
        }
    }

    /**
     * Moves Clyde
     */
    @Override
    public void update() {
        Point ghostLocation = new Point(getX(), getY());

        // Handle game start edge case
        if(pacManLocation == null) {
            return;
        }
        double distanceToPacMan = ghostLocation.distance(pacManLocation);

        Point targetLocation;
        // If Clyde is more than 8 cells away from PacMan, target PacMan
        if (distanceToPacMan > 8 && !isFrightened) {
            targetLocation = pacManLocation;
        // Otherwise, target the bottom left corner
        } else {
            targetLocation = new Point(1, 29);
        }

        Point nextCell = ShortestPathFinder.findNextCellForShortestPath(ghostLocation, targetLocation);

        // Calculate direction
        int xDiff = nextCell.x - getX();
        int yDiff = nextCell.y - getY();

        if(xDiff == 1){
            currentDirection = "right";
        } else if(xDiff == -1){
            currentDirection = "left";
        } else if(yDiff == 1){
            currentDirection = "down";
        } else {
            currentDirection = "up";
        }
        // If Clyde is dead or should not leave home, update towards his starting location
        if(isDead || !observer.shouldClydeLeaveHome())
            nextCell = ShortestPathFinder.findNextCellForShortestPath(ghostLocation, startingLocation);
        // Move Clyde
        x = nextCell.x;
        y = nextCell.y;
    }
}
