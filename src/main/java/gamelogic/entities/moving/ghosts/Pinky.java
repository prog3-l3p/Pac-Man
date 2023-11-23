package gamelogic.entities.moving.ghosts;

import gamelogic.entities.Entity;
import utility.ResourceHandler;
import utility.ShortestPathFinder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static utility.GameConstants.COLUMN_COUNT;
import static utility.GameConstants.ROW_COUNT;

public class Pinky extends Ghost  {
    public Pinky(int x, int y) {
        super(x, y);
        setSprite(RIGHT_1);
    }

    @Override
    public BufferedImage getSprite() {
        getNextAnimation();
        if(isFrightened){
            return ResourceHandler.getSpriteMap("frightened").get(spriteName);
        }
        return ResourceHandler.getSpriteMap("pinky").get(spriteName);
    }

    @Override
    public boolean isTraversableByPacMan() {
        return true;
    }


    public void move(){
        Point ghostLocation = new Point(getX(), getY());
        Point ambushLocation;

        if (isPacManMovingTowardsPinky()) {
            ambushLocation = getTileBehindPinky();
        } else {
            ambushLocation = switch (pacManDirection){
                case "up" -> new Point((pacManLocation.x) % COLUMN_COUNT, (pacManLocation.y - 4) % ROW_COUNT);
                case "left" -> new Point((pacManLocation.x - 4) % COLUMN_COUNT, (pacManLocation.y) % ROW_COUNT);
                case "down" -> new Point((pacManLocation.x) % COLUMN_COUNT, (pacManLocation.y + 4) % ROW_COUNT);
                case "right" -> new Point((pacManLocation.x + 4) % COLUMN_COUNT, (pacManLocation.y) % ROW_COUNT);
                default -> throw new IllegalStateException("Unexpected value: " + pacManDirection);
            };
        }

        // Make sure ambush location is within the bounds of the map
        boundCheckAmbushLocation(ambushLocation);

        // Make sure ambush location is traversable
        traverseCheckAmbushLocation(ambushLocation);

        Point nextCell = ShortestPathFinder.findNextCellForShortestPath(ghostLocation, ambushLocation);

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
            nextCell = ShortestPathFinder.findNextCellForShortestPath(ghostLocation, new Point(1, 1));

        x = nextCell.x % COLUMN_COUNT;
        y = nextCell.y % ROW_COUNT;


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

    private Point getTileBehindPinky() {
        return switch (currentDirection){
            case "up" -> new Point((getX()) % COLUMN_COUNT, (getY() + 2) % ROW_COUNT);
            case "left" -> new Point((getX() + 2) % COLUMN_COUNT, (getY()) % ROW_COUNT);
            case "down" -> new Point((getX()) % COLUMN_COUNT, (getY() - 2) % ROW_COUNT);
            case "right" -> new Point((getX() - 2) % COLUMN_COUNT, (getY()) % ROW_COUNT);
            default -> throw new IllegalStateException("Unexpected value: " + currentDirection);
        };
    }

    private boolean isPacManMovingTowardsPinky() {
        if(currentDirection.equals("up") && pacManDirection.equals("down")) return true;
        if(currentDirection.equals("down") && pacManDirection.equals("up")) return true;
        if(currentDirection.equals("left") && pacManDirection.equals("right")) return true;
        if(currentDirection.equals("right") && pacManDirection.equals("left")) return true;
        return false;
    }
}
