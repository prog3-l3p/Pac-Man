package entities.moving.ghosts;

import entities.Entity;
import utility.ResourceHandler;
import utility.ShortestPathFinder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Pinky extends Ghost  {
    public Pinky(int x, int y) {
        super(x, y);
        setSprite(RIGHT_1);
    }

    @Override
    public BufferedImage getSprite() {
        getNextAnimation();
        return ResourceHandler.getSpriteMap("pinky").get(spriteName);
    }

    @Override
    public boolean isTraversableByPacMan() {
        return false;
    }

    public void move(){
        Point ghostLocation = new Point(getX(), getY());
        Point ambushLocation = switch (pacManDirection){
            case "up" -> new Point((pacManLocation.x) % 28, (pacManLocation.y - 2) % 31);
            case "left" -> new Point((pacManLocation.x - 2) % 28, (pacManLocation.y) % 31);
            case "down" -> new Point((pacManLocation.x) % 28, (pacManLocation.y + 2) % 31);
            case "right" -> new Point((pacManLocation.x + 2) % 28, (pacManLocation.y) % 31);
            default -> throw new IllegalStateException("Unexpected value: " + pacManDirection);

        };

        // Ambush location cannot be outside the map
        if (ambushLocation.x < 0) ambushLocation.x += 28;
        if (ambushLocation.y < 0) ambushLocation.y += 31;
        if (ambushLocation.x >= 28) ambushLocation.x -= 28;
        if (ambushLocation.y >= 31) ambushLocation.y -= 31;

        Point nextCell = ShortestPathFinder.findNextCellForShortestPath(ghostLocation, ambushLocation);

        // If the next cell is not traversable, fallback to Blinky's behaviour
        ArrayList<ArrayList<Entity>> level = ResourceHandler.getCurrentLevel();
        if (!level.get(ambushLocation.y).get(ambushLocation.x).isTraversableByGhosts()) {
            nextCell = ShortestPathFinder.findNextCellForShortestPath(ghostLocation, pacManLocation);
        }

        x = nextCell.x;
        y = nextCell.y;
    }
}
