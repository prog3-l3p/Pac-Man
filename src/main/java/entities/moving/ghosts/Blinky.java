package entities.moving.ghosts;

import utility.ResourceHandler;
import utility.ShortestPathFinder;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Blinky extends Ghost{

    private static final int ROW_COUNT = 31;
    private static final int COLUMN_COUNT = 28;

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
        Point nextCell = ShortestPathFinder.findNextCellForShortestPath(ghostLocation, pacManLocation);
        x = nextCell.x;
        y = nextCell.y;
        if(x <= 0) {x += COLUMN_COUNT;}
        if(y <= 0) {y += ROW_COUNT;}
    }


}
