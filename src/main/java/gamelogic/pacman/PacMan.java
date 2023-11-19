package gamelogic.pacman;

import gamelogic.Entity;
import resourcehandler.ResourceHandler;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 * This class represents the PacMan entity
 */
public class PacMan extends Entity {
    private String currentDirection = NEUTRAL;
    private int speedX;
    private int speedY;
    private static final int COLUMN_COUNT = 28;
    private static final int ROW_COUNT = 31;
    private static final String NEUTRAL = "neutral";
    private static final String LEFT_1 = "left_1";
    private static final String LEFT_2 = "left_2";
    private static final String RIGHT_1 = "right_1";
    private static final String RIGHT_2 = "right_2";
    private static final String UP_1 = "up_1";
    private static final String UP_2 = "up_2";
    private static final String DOWN_1 = "down_1";
    private static final String DOWN_2 = "down_2";

    /**
     * Constructor for PacMan
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public PacMan(int x, int y) {
        super(x ,y);
        setSprite(NEUTRAL);
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
        Entity e = ResourceHandler.getCurrentLevel().get((x + speedX) % COLUMN_COUNT).get((y + speedY) % ROW_COUNT);
        if(e.isTraversableByPacMan()) {
            x = (x + speedX) % COLUMN_COUNT;
            y = (y + speedY) % ROW_COUNT;
        }
        e.eat();
    }

    /**
     * Used for the menu screen. Moves PacMan to the right.
     * @param screenWidth the width of the menu window
     */
    public void menuMove(int screenWidth){
        currentDirection = "right";
        if(x + 1 <= screenWidth) x += 1;
        else x = 0;
    }

    /**
     * Sets the current sprite of PacMan based on his previous sprite and the direction he is facing.
     * @return the current sprite of PacMan
     */
    public BufferedImage getSprite(){
        switch(currentDirection){
            case "left" -> {
                if(spriteName.equals(LEFT_1)){
                    setSprite(LEFT_2);
                } else {
                    setSprite(LEFT_1);
                }
            }
            case "right" -> {
                if(spriteName.equals(RIGHT_1)){
                    setSprite(RIGHT_2);
                } else {
                    setSprite(RIGHT_1);
                }
            }
            case "up" -> {
                if(spriteName.equals(UP_1)){
                    setSprite(UP_2);
                } else {
                    setSprite(UP_1);
                }
            }
            case "down" -> {
                if(spriteName.equals(DOWN_1)){
                    setSprite(DOWN_2);
                } else {
                    setSprite(DOWN_1);
                }
            }
        }

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