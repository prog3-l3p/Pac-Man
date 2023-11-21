package entities.moving;

import entities.Entity;
import utility.ResourceHandler;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 * This class represents the PacMan entity
 */
public class PacMan extends MovingEntity {
    private static final int COLUMN_COUNT = 28;
    private static final int ROW_COUNT = 31;

    /**
     * Constructor for PacMan
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public PacMan(int x, int y) {
        super(x ,y);

    }

    /**
     * Moves PacMan in the direction he is currently facing.
     * If PacMan is at the edge of the map (left or right side), he will wrap around to the other side.
     * If PacMan is facing a wall, he will not move.
     * If PacMan is facing food, he will eat it.
     */
    @Override
    public void move(){
        if(x <= 0) {x += COLUMN_COUNT;}
        if(y <= 0) {y += ROW_COUNT;}
        Entity e = ResourceHandler.getCurrentLevel().get((y + speedY) % ROW_COUNT).get((x + speedX) % COLUMN_COUNT);
        if(e.isTraversableByPacMan()) {
            x = (x + speedX) % COLUMN_COUNT;
            y = (y + speedY) % ROW_COUNT;
        }
        if(e.isEdible())
            e.eatenBy(this);
    }

    /**
     * Used for the menu screen. Moves PacMan to the right.
     * @param screenWidth the width of the menu window
     */
    public void menuMove(int screenWidth){
        if(x >= screenWidth) x = 0;
        x += 1;
    }

    public void setInitialDirection(String direction){
        currentDirection = direction;
    }

    /**
     * Sets the current sprite of PacMan based on his previous sprite and the direction he is facing.
     * @return the current sprite of PacMan
     */
    public BufferedImage getSprite(){
        getNextAnimation();
        return ResourceHandler.getSpriteMap("pacman").get(spriteName);
    }

    @Override
    public boolean isTraversableByPacMan() {
        return true;
    }

    /**
     * Sets the current direction of PacMan based on the key pressed.
     * @param e the key pressed
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch(key){
            case KeyEvent.VK_LEFT -> {
                currentDirection = "left";
                speedX = -1;
                speedY = 0;
            }
            case KeyEvent.VK_RIGHT -> {
                currentDirection = "right";
                speedX = 1;
                speedY = 0;
            }
            case KeyEvent.VK_UP ->  {
                currentDirection = "up";
                speedY = -1;
                speedX = 0;
            }
            case KeyEvent.VK_DOWN -> {
                currentDirection = "down";
                speedY = 1;
                speedX = 0;
            }
        }
    }

    @Override
    public PacMan isPacMan() {
        return this;
    }

}