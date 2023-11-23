package gamelogic.entities;

import gamelogic.EntityObserver;
import gamelogic.entities.moving.PacMan;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * Abstract class for all entities in the game.
 * Entities are objects that are drawn on the screen and can be interacted with.
 */
public abstract class Entity implements Serializable {
    protected int x;
    protected int y;
    protected String spriteName;
    protected boolean traversableByPacMan = true;
    protected boolean traversableByGhosts = true;
    protected transient EntityObserver observer;

    /**
     * Constructor for the entity.
     * @param x The x coordinate of the entity.
     * @param y The y coordinate of the entity.
     */
    protected Entity(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Draws the entity on the screen.
     * @param g Graphics object used to draw the entity.
     */
    public void draw(Graphics g){
        g.drawImage(getSprite(), getX() * getWidth(), getY() * getHeight(), null);
    }

    /**
     * @return The x coordinate of the entity.
     */
    public int getX() {
        return x;
    }

    /**
     * @return The y coordinate of the entity.
     */
    public int getY(){
        return y;
    }

    /**
     * @return The width of the entity.
     */
    public int getWidth(){
        return getSprite().getWidth(null);
    }
    /**
     * @return The height of the entity.
     */
    public int getHeight(){
        return getSprite().getHeight(null);
    }
    /**
     * @return The sprite of the entity.
     */
    public abstract BufferedImage getSprite();

    /**
     * Sets the sprite of the entity.
     * @param spriteName The name of the sprite.
     */
    public void setSprite(String spriteName){
        this.spriteName = spriteName;
    }

    /**
     * Sets the entity to be not traversable by PacMan.
     */
    public void setNotTraversableByPacMan() {
        traversableByPacMan = false;
    }

    /**
     * Sets the entity to be not traversable by ghosts.
     */
    public void setNotTraversableByGhosts() {
        traversableByGhosts = false;
    }

    /**
     * @return Whether the entity is traversable by PacMan.
     */
    public boolean isTraversableByPacMan(){
        return traversableByPacMan;
    }

    /**
     * @return Whether the entity is traversable by ghosts.
     */
    public boolean isTraversableByGhosts(){
        return traversableByGhosts;
    }

    /**
     * @return Whether the entity is edible.
     */
    public boolean isEdible() {
        return false;
    }

    /**
     * Called when the entity is eaten by PacMan.
     */
    public void eatenBy(PacMan pacMan) {
    }

    /**
     * Moves the entity.
     */
    public void move() {
    }

    /**
     * Adds an observer to the entity.
     * @param observer The observer to be added.
     */
    public void addObserver(EntityObserver observer) {
        this.observer = observer;
    }

}
