package gamelogic;

import gamelogic.pacman.PacMan;

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
     * @return Whether the entity is traversable by PacMan.
     */
    public boolean isTraversableByPacMan(){
        return traversableByPacMan;
    }

    public void eat() {
    }

    public void move() {
    }

    public PacMan isPacMan() {
        return null;
    }
}
