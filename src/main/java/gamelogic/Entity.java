package gamelogic;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;


public abstract class Entity implements Serializable {
    protected int x;
    protected int y;
    protected String spriteName;
    protected boolean traversableByPacMan = true;
    protected Entity(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g){
        g.drawImage(getSprite(), getX() * getWidth(), getY() * getHeight(), null);
    }

    public int getX() {
        return x;
    }


    public int getY(){
        return y;
    }

    public int getWidth(){
        return getSprite().getWidth(null);
    }

    public int getHeight(){
        return getSprite().getHeight(null);
    }

    public abstract BufferedImage getSprite();
    public void setSprite(String spriteName){
        this.spriteName = spriteName;
    }

    public void setNotTraversableByPacMan() {
        traversableByPacMan = false;
    }

    public boolean isTraversableByPacMan(){
        return traversableByPacMan;
    }

    public void eat() {
    }
}
