package gamelogic.entities.moving.ghosts;

import utility.ResourceHandler;
import utility.ShortestPathFinder;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utility.GameConstants.COLUMN_COUNT;
import static utility.GameConstants.ROW_COUNT;

/**
 * This class represents Blinky
 */
public class Blinky extends Ghost{

    /**
     * Constructor for Blinky
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public Blinky(int x, int y) {
        super(x, y);
        startingLocation = new Point(x, y);
        setSprite(RIGHT_1);
    }

    /**
     * Gets the sprite for Blinky
     * @return current sprite
     */
    @Override
    public BufferedImage getSprite() {
        getNextAnimation();
        // If Blinky is frightened, return the frightened sprite
        if(isFrightened){
            return ResourceHandler.getSpriteMap("frightened").get(spriteName);
        // If Blinky is dead, return the dead sprite
        } else if (isDead){
            return ResourceHandler.getSpriteMap("dead").get(spriteName);
        // Otherwise, return the normal sprite
        } else {
            return ResourceHandler.getSpriteMap("blinky").get(spriteName);
        }
    }

    /**
     * Moves Blinky
     */
    @Override
    public void update(){
        Point ghostLocation = new Point(getX(), getY());
        // Get the second step on the shortest path
        Point nextCell = ShortestPathFinder.findNextCellForShortestPath(ghostLocation, pacManLocation);

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

        // If Blinky is frightened, update towards the top right corner
        if(isFrightened){
            nextCell = ShortestPathFinder.findNextCellForShortestPath(ghostLocation, new Point(26, 1));
        }
        // If Blinky is dead, update towards the starting location
        if(isDead) {
            nextCell = ShortestPathFinder.findNextCellForShortestPath(ghostLocation, startingLocation);
        }

        // Update location according to pathfinding
        x = nextCell.x % COLUMN_COUNT;
        y = nextCell.y % ROW_COUNT;
    }

}
