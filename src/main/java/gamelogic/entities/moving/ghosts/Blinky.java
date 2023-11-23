package gamelogic.entities.moving.ghosts;

import utility.ResourceHandler;
import utility.ShortestPathFinder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import static utility.GameConstants.COLUMN_COUNT;
import static utility.GameConstants.ROW_COUNT;

public class Blinky extends Ghost{
    public Blinky(int x, int y) {
        super(x, y);
        setSprite(RIGHT_1);
    }

    @Override
    public BufferedImage getSprite() {
        getNextAnimation();
        if(isFrightened){
            return ResourceHandler.getSpriteMap("frightened").get(spriteName);
        }
        return ResourceHandler.getSpriteMap("blinky").get(spriteName);
    }

    @Override
    public boolean isTraversableByPacMan() {
        return true;
    }


    @Override
    public void move(){
        Point ghostLocation = new Point(getX(), getY());
        // Get the second step on the shortest path
        Point nextCell = ShortestPathFinder.findNextCellForShortestPath(ghostLocation, pacManLocation);

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

        if(isFrightened){
            nextCell = ShortestPathFinder.findNextCellForShortestPath(ghostLocation, new Point(26, 1));
        }

        x = nextCell.x % COLUMN_COUNT;
        y = nextCell.y % ROW_COUNT;
    }

}
