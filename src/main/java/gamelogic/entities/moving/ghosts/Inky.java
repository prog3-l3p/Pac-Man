package gamelogic.entities.moving.ghosts;

import gamelogic.entities.Entity;
import utility.ResourceHandler;
import utility.ShortestPathFinder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static utility.GameConstants.COLUMN_COUNT;
import static utility.GameConstants.ROW_COUNT;

public class Inky extends Ghost {
    private Blinky observedBlinky;
    public Inky(int x, int y) {
        super(x, y);
        setSprite(RIGHT_1);
    }

    @Override
    public BufferedImage getSprite() {
        if(isFrightened){
            return ResourceHandler.getSpriteMap("frightened").get(spriteName);
        }
        return ResourceHandler.getSpriteMap("inky").get(spriteName);
    }

    @Override
    public boolean isTraversableByPacMan() {
        return true;
    }

    @Override
    public void move() {
        final Point target = getTarget();

        Point ghostLocation = new Point(getX(), getY());
        // Make sure ambush location is within the bounds of the map
        boundCheckAmbushLocation(target);

        // Make sure ambush location is traversable
        traverseCheckAmbushLocation(target);

        Point nextCell = ShortestPathFinder.findNextCellForShortestPath(ghostLocation, target);

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

        if(isFrightened)
            nextCell = ShortestPathFinder.findNextCellForShortestPath(ghostLocation, new Point(26, 29));
        x = nextCell.x % COLUMN_COUNT;
        y = nextCell.y % ROW_COUNT;
    }

    private Point getTarget() {
        System.out.println("PacMan location: " + pacManLocation);
        System.out.println("PacMan direction: " + pacManDirection);
        Point pacManPlusTwo = switch (pacManDirection){
            case "up" -> new Point(pacManLocation.x, pacManLocation.y - 2);
            case "left" -> new Point(pacManLocation.x - 2, pacManLocation.y);
            case "down" -> new Point(pacManLocation.x, pacManLocation.y + 2);
            case "right" -> new Point(pacManLocation.x + 2, pacManLocation.y);
            default -> throw new IllegalStateException("Unexpected value: " + pacManDirection);
        };

        Point blinkyLocation = new Point(observedBlinky.getX(), observedBlinky.getY());
        return new Point((pacManPlusTwo.x - blinkyLocation.x) * 2, (pacManPlusTwo.y - blinkyLocation.y) * 2);
    }

    public void setObservedBlinky(Blinky observedBlinky){
        this.observedBlinky = observedBlinky;
    }

    private void boundCheckAmbushLocation(Point ambushLocation){
        while(ambushLocation.x <= 0) ambushLocation.x += 1;
        while(ambushLocation.y <= 0) ambushLocation.y += 1;
        while(ambushLocation.x >= COLUMN_COUNT) ambushLocation.x -= 1;
        while(ambushLocation.y >= ROW_COUNT) ambushLocation.y -= 1;
    }

    private void traverseCheckAmbushLocation(Point ambushLocation){
        ArrayList<ArrayList<Entity>> level = ResourceHandler.getCurrentLevel();
        if(ambushLocation.x < 0 || ambushLocation.x >= COLUMN_COUNT || ambushLocation.y < 0 || ambushLocation.y >= ROW_COUNT){
            ambushLocation = ShortestPathFinder.findNextCellForShortestPath(new Point(getX(), getY()), pacManLocation);
        }
        if(level.get(ambushLocation.y).get(ambushLocation.x).isTraversableByGhosts()){
            // If the ambush location is already traversable, return without making any changes
            return;
        }
        while(!level.get(ambushLocation.y).get(ambushLocation.x).isTraversableByGhosts()) {
            // If the current location is not traversable, move the ambush location one step towards the ghost
            if (ambushLocation.x < getX()) ambushLocation.x += 1;
            else if (ambushLocation.x > getX()) ambushLocation.x -= 1;
            if (ambushLocation.y < getY()) ambushLocation.y += 1;
            else if (ambushLocation.y > getY()) ambushLocation.y -= 1;

            // Check if the new ambush location is out of bounds
            if (ambushLocation.x < 0 || ambushLocation.x >= level.get(0).size() || ambushLocation.y < 0 || ambushLocation.y >= level.size()) {
                // If the new ambush location is out of bounds, return without making any changes
                return;
            }
        }
    }

}
