package entities.moving.ghosts;

import utility.ResourceHandler;
import utility.ShortestPathFinder;

import java.awt.*;
import java.awt.image.BufferedImage;

//TODO: implement movement logic
public class Blinky extends Ghost{

    public Blinky(int x, int y) {
        super(x, y);
        setSprite(RIGHT_1);
    }

    @Override
    public BufferedImage getSprite() {
        getNextAnimation();
        return ResourceHandler.getSpriteMap("blinky").get(spriteName);
    }

    @Override
    public boolean isTraversableByPacMan() {
        return true;
    }


    @Override
    public void move(){

        Point ghostLocation = new Point(getX(), getY());
        Point pacManLocation = new Point(pacManObserver.getPacManX(), pacManObserver.getPacManY());
        Point nextCell = ShortestPathFinder.findNextCellForShortestPath(ghostLocation, pacManLocation);
        x = nextCell.x;
        y = nextCell.y;
    }


}
