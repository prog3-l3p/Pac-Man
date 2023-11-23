package gamelogic.entities.moving.ghosts;

import utility.ResourceHandler;
import utility.ShortestPathFinder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Clyde extends Ghost {
    public Clyde(int x, int y) {
        super(x, y);
        setSprite(RIGHT_1);
    }

    @Override
    public BufferedImage getSprite() {
        getNextAnimation();
        if (isFrightened) {
            return ResourceHandler.getSpriteMap("frightened").get(spriteName);
        }
        return ResourceHandler.getSpriteMap("clyde").get(spriteName);
    }

    @Override
    public boolean isTraversableByPacMan() {
        return true;
    }

    @Override
    public void move() {
        Point ghostLocation = new Point(getX(), getY());

        double distanceToPacMan = ghostLocation.distance(pacManLocation);

        Point targetLocation;
        if (distanceToPacMan > 8 && !isFrightened) {
            targetLocation = pacManLocation;
        } else {
            // Target is the fixed tile outside the bottom-left corner of the maze
            targetLocation = new Point(1, 29);
        }

        Point nextCell = ShortestPathFinder.findNextCellForShortestPath(ghostLocation, targetLocation);

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


        x = nextCell.x;
        y = nextCell.y;
    }
}
