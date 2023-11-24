package gamelogic.entities.moving.ghosts;

import gamelogic.entities.moving.MovingEntity;
import gamelogic.entities.moving.PacMan;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utility.GameConstants.TIMER_DELAY;

/**
 * This class represents an abstract ghost
 */
public abstract class Ghost extends MovingEntity {
    /**
     * The starting location of the ghost
     */
    Point startingLocation;
    /**
     * True if the ghost is frightened, false otherwise
     */
    protected boolean isFrightened;
    /**
     * True if the ghost is dead, false otherwise
     */
    protected boolean isDead;
    /**
     * The current location of PacMan
     */
    protected Point pacManLocation;
    /**
     * The current direction of PacMan
     */
    protected String pacManDirection;
    /**
     * The number of frames the ghost is dead for
     */
    protected int deathTimer;

    /**
     * Constructor for the ghost
     * @param x x-coordinate
     * @param y y-coordinate
     */
    protected Ghost(int x, int y) {
        super(x, y);
        startingLocation = new Point(x, y);
        // Ghosts should be able to traverse through each other
        traversableByGhosts = true;
    }

    /**
     * Gets the sprite of the ghost
     * @return the sprite of the ghost
     */
    public abstract BufferedImage getSprite();

    /**
     * Checks if the ghost is edible
     * @return true if the ghost is frightened, false otherwise
     */
    @Override
    public boolean isEdible(){
        return isFrightened;
    }

    /**
     * Tries to eat the ghost, fails if the ghost is not frightened
     * @param pacMan the PacMan that eats the ghost
     */
    @Override
    public void eatenBy(PacMan pacMan){
        // If PacMan is under the effects of a power pellet, eat the ghost
        if(pacMan.canEatGhosts()) {
            if(!isDead)
                deathTimer = 6000/TIMER_DELAY;
            isDead = true;
            isFrightened = false;
            // Add 200 points to the score
            if (observer != null) {
                observer.addScore(200);
            }
        }
    }

    /**
     * Updates the ghost's location, direction, and frightened state
     * @param newLocation the new location of the ghost
     * @param newDirection the new direction of the ghost
     * @param canEatGhosts true if PacMan can eat the ghost, false otherwise
     */
    public void update(Point newLocation, String newDirection, boolean canEatGhosts){
        pacManLocation = newLocation;
        pacManDirection = newDirection;
        if(!isDead)
            isFrightened = canEatGhosts;
        if(isDead && new Point(getX(), getY()).equals(startingLocation))
            deathTimer--;
        if(deathTimer == 0)
            isDead = false;
    }

    /**
     * Checks if the ghost is dead
     * @return true if the ghost is dead, false otherwise
     */
    public boolean isDead() {
        return isDead;
    }
}
