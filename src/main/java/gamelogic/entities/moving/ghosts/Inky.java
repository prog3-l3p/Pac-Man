package gamelogic.entities.moving.ghosts;

import gamelogic.entities.Entity;
import utility.ResourceHandler;
import utility.ShortestPathFinder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static utility.GameConstants.COLUMN_COUNT;
import static utility.GameConstants.ROW_COUNT;

/**
 * This class represents Inky
 */
public class Inky extends Ghost {
    /**
     * The Blinky entity observed by Inky
     */
    private Blinky observedBlinky;

    /**
     * Constructor for Inky
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public Inky(int x, int y) {
        super(x, y);
        setSprite(RIGHT_1);
    }

    /**
     * Gets the sprite for Inky
     * @return the sprite for Inky
     */
    @Override
    public BufferedImage getSprite() {
        getNextAnimation();
        // If frightened, return frightened sprite
        if(isFrightened){
            return ResourceHandler.getSpriteMap("frightened").get(spriteName);
        // If dead, return dead sprite
        } else if(isDead){
            return ResourceHandler.getSpriteMap("dead").get(spriteName);
        // Otherwise, return normal sprite
        } else {
            return ResourceHandler.getSpriteMap("inky").get(spriteName);
        }
    }

    /**
     * Moves Inky
     */
    @Override
    public void update() {
        // Get Inky's target location
        final Point target = getTarget();

        Point ghostLocation = new Point(getX(), getY());
        // Make sure ambush location is within the bounds of the map
        boundCheckAmbushLocation(target);

        // Make sure ambush location is traversable
        traverseCheckAmbushLocation(target);

        Point nextCell = ShortestPathFinder.findNextCellForShortestPath(ghostLocation, target);

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

        // If frightened, update towards the bottom right corner
        if(isFrightened)
            nextCell = ShortestPathFinder.findNextCellForShortestPath(ghostLocation, new Point(26, 29));
        // If not supposed to leave home or dead, update towards the starting location
        if(isDead || !observer.shouldInkyLeaveHome()){
            nextCell = ShortestPathFinder.findNextCellForShortestPath(ghostLocation, startingLocation);
        }
        // Update location
        x = nextCell.x % COLUMN_COUNT;
        y = nextCell.y % ROW_COUNT;
    }

    /**
     * Gets the target location for Inky
     * @return the target location
     */
    private Point getTarget() {
        // get the cell two steps ahead of PacMan
        if(pacManLocation == null)
            return new Point(0, 0);
        Point pacManPlusTwo = switch (pacManDirection){
            case "up" -> new Point(pacManLocation.x, pacManLocation.y - 2);
            case "left" -> new Point(pacManLocation.x - 2, pacManLocation.y);
            case "down" -> new Point(pacManLocation.x, pacManLocation.y + 2);
            case "right" -> new Point(pacManLocation.x + 2, pacManLocation.y);
            default -> throw new IllegalStateException("Unexpected value: " + pacManDirection);
        };

        // get the location of Blinky
        Point blinkyLocation = new Point(observedBlinky.getX(), observedBlinky.getY());
        // get the vector from Blinky to the cell two steps ahead of PacMan and double it
        return new Point((pacManPlusTwo.x - blinkyLocation.x) * 2, (pacManPlusTwo.y - blinkyLocation.y) * 2);
    }

    /**
     * Sets the observed Blinky
     * @param observedBlinky the observed Blinky
     */
    public void setObservedBlinky(Blinky observedBlinky){
        this.observedBlinky = observedBlinky;
    }

    /**
     * Makes sure the ambush location is within the bounds of the map
     * @param ambushLocation the ambush location
     */
    private void boundCheckAmbushLocation(Point ambushLocation){
        // Adjust the ambush location if it is out of bounds
        while(ambushLocation.x <= 0) ambushLocation.x += 1;
        while(ambushLocation.y <= 0) ambushLocation.y += 1;
        while(ambushLocation.x >= COLUMN_COUNT) ambushLocation.x -= 1;
        while(ambushLocation.y >= ROW_COUNT) ambushLocation.y -= 1;
    }

    /**
     * Makes sure the ambush location is traversable
     * @param ambushLocation the ambush location
     */
    private void traverseCheckAmbushLocation(Point ambushLocation){
        ArrayList<ArrayList<Entity>> level = ResourceHandler.getCurrentLevel();

        // Needed to prevent infinite loop
        int maxIterations = ROW_COUNT * COLUMN_COUNT;
        int iterations = 0;

        // Check if the ambush location is out of bounds
        if(ambushLocation.x < 0 || ambushLocation.x >= COLUMN_COUNT || ambushLocation.y < 0 || ambushLocation.y >= ROW_COUNT){
            // If it is fall back to Blinky's behaviour
            ambushLocation = ShortestPathFinder.findNextCellForShortestPath(new Point(getX(), getY()), pacManLocation);
        }
        if(level.get(ambushLocation.y).get(ambushLocation.x).isTraversableByGhosts()){
            // If the ambush location is already traversable, return without making any changes
            return;
        }
        while(!level.get(ambushLocation.y).get(ambushLocation.x).isTraversableByGhosts() && iterations < maxIterations) {
            // If the current location is not traversable, update the ambush location one step towards the ghost
            if (ambushLocation.x < getX())
                ambushLocation.x += 1;
            else if (ambushLocation.x > getX())
                ambushLocation.x -= 1;
            else if (ambushLocation.y < getY())
                ambushLocation.y += 1;
            else if (ambushLocation.y > getY())
                ambushLocation.y -= 1;
                // Do nothing
            else {
            }

            // Check if the new ambush location is out of bounds
            if (ambushLocation.x < 0 || ambushLocation.x >= level.get(0).size() || ambushLocation.y < 0 || ambushLocation.y >= level.size()) {
                // If the new ambush location is out of bounds, return without making any changes
                return;
            }
            iterations++;
        }
    }

}
