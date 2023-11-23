package gamelogic.entities.moving;

import gamelogic.entities.Entity;
import gamelogic.entities.moving.ghosts.Ghost;
import utility.ResourceHandler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static utility.GameConstants.TIMER_DELAY;

/**
 * This class represents the PacMan entity
 */
public class PacMan extends MovingEntity {
    private final ArrayList<Ghost> observers = new ArrayList<>();
    private static final int COLUMN_COUNT = 28;
    private static final int ROW_COUNT = 31;
    private boolean canEatGhosts;
    private int ghostEatingTimer = 0;
    private boolean dead;

    /**
     * Constructor for PacMan
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public PacMan(int x, int y) {
        super(x ,y);
        currentDirection = "right";
    }

    /**
     * Moves PacMan in the direction he is currently facing.
     * If PacMan is at the edge of the map (left or right side), he will wrap around to the other side.
     * If PacMan is facing a wall, he will not move.
     * If PacMan is facing food, he will eat it.
     */
    @Override
    public void move(){
        // Wrap around
        if(x <= 0) {x += COLUMN_COUNT;}
        if(y <= 0) {y += ROW_COUNT;}
        // Check if PacMan is facing a cell that is traversable
        Entity e = ResourceHandler.getCurrentLevel().get((y + speedY) % ROW_COUNT).get((x + speedX) % COLUMN_COUNT);
        if(e.isTraversableByPacMan()) {
            x = (x + speedX) % COLUMN_COUNT;
            y = (y + speedY) % ROW_COUNT;
        }
        // Try to eat the entity
        e.eatenBy(this);
        // Check if PacMan is facing a ghost
        for(Ghost g : observers){
            if(g.getX() == x && g.getY() == y){
                if(canEatGhosts){
                    g.eatenBy(this);
                } else if (!g.isDead()){
                    dead = true;
                }
            }
        }
        ghostEatingTimer--;
        if(ghostEatingTimer == 0)
            canEatGhosts = false;
        notifyObservers();
    }

    /**
     * Used for the menu screen. Moves PacMan to the right.
     * @param screenWidth the width of the menu window
     */
    public void menuMove(int screenWidth){
        if(x >= screenWidth) x = 0;
        x += 1;
    }

    /**
     * Sets the initial direction of PacMan (only used for the menu screen).
     * @param direction the initial direction of PacMan
     */
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
    public boolean isEdible(){
        return true;
    }

    /**
     * @return whether PacMan is dead or not
     */
    public boolean isDead() {
        return dead;
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

    /**
     * Adds a ghost to the list of observers
     * @param ghost the ghost to be added
     */
    public void addObserver(Ghost ghost){
        observers.add(ghost);
    }

    /**
     * Sets whether PacMan can eat ghosts or not
     * @param canEatGhosts whether PacMan can eat ghosts or not
     */
    public void setCanEatGhosts(boolean canEatGhosts) {
        this.canEatGhosts = canEatGhosts;
        ghostEatingTimer = 3500/TIMER_DELAY;
    }

    /**
     * @return whether PacMan can eat ghosts or not
     */
    public boolean canEatGhosts() {
        return canEatGhosts;
    }

    /**
     * Notifies all observers of PacMan's current location, direction and whether he can eat ghosts or not
     */
    private void notifyObservers(){
        for(Ghost ghost : observers){
            ghost.update(new Point(x, y), currentDirection, canEatGhosts);
        }
    }

}