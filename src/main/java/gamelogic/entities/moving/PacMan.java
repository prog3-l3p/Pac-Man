package gamelogic.entities.moving;

import gamelogic.entities.Entity;
import gamelogic.entities.moving.ghosts.Ghost;
import utility.ResourceHandler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static utility.GameConstants.*;

/**
 * This class represents the PacMan entity
 */
public class PacMan extends MovingEntity {
    /**
     * Stores the observers of PacMan
     */
    private final ArrayList<Ghost> observers = new ArrayList<>();
    /**
     * Stores whether PacMan can eat ghosts or not
     */
    private boolean canEatGhosts;
    /**
     * Stores the number of frames PacMan can eat ghosts for
     */
    private int ghostEatingTimer = 0;
    /**
     * Stores whether PacMan is dead or not
     */
    private boolean dead;

    /**
     * Constructor for PacMan
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public PacMan(int x, int y) {
        super(x ,y);
        currentDirection = "neutral";
    }

    /**
     * Updates PacMan's location and checks if he is facing a ghost or not
     * If he is, he either eats the ghost or dies
     * If he is facing a cell that is traversable, he moves to that cell
     */
    @Override
    public void update(){
        determineSpeed();
        ghostEatingTimer--;
        if(ghostEatingTimer == 0)
            canEatGhosts = false;
        for(Ghost g : observers){
            if(g.getX() == x && g.getY() == y){
                g.eatenBy(this);
            }
        }
        // Check if PacMan is facing a cell that is traversable
        Entity e = ResourceHandler.getLevelEntities().get((y + speedY) % ROW_COUNT).get((x + speedX) % COLUMN_COUNT);
        // Try to eat the entity
        if(e.isTraversableByPacMan()) {
            x = (x + speedX) % COLUMN_COUNT;
            y = (y + speedY) % ROW_COUNT;
            e.eatenBy(this);
        }
        // Wrap around
        if(x <= 0) {x += COLUMN_COUNT;}
        if(y <= 0) {y += ROW_COUNT;}
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
        currentDirection = switch(key){
            case KeyEvent.VK_LEFT -> "left";
            case KeyEvent.VK_RIGHT -> "right";
            case KeyEvent.VK_UP -> "up";
            case KeyEvent.VK_DOWN -> "down";
            default -> currentDirection;
        };
    }

    private void determineSpeed(){
        switch(currentDirection){
            case "left" -> {
                speedX = -1;
                speedY = 0;
            }
            case "right" -> {
                speedX = 1;
                speedY = 0;
            }
            case "up" -> {
                speedX = 0;
                speedY = -1;
            }
            case "down" -> {
                speedX = 0;
                speedY = 1;
            }
            default -> {
                speedX = 0;
                speedY = 0;
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

    public void kill(){
        dead = true;
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