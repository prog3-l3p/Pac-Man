package entities.moving.ghosts;

import entities.Entity;
import entities.moving.MovingEntity;
import entities.moving.PacMan;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Ghost extends MovingEntity {
    protected boolean isFrightened;
    protected boolean isDead;
    protected Ghost(int x, int y) {
        super(x, y);
    }
    protected Point pacManLocation;
    protected String pacManDirection;
    public abstract BufferedImage getSprite();

    @Override
    public boolean isEdible(){
        return isFrightened;
    }

    @Override
    public void eatenBy(Entity e){
        if(e.isPacMan() == null || !isEdible()) return;

    }
    @Override
    public Ghost isGhost(){
        return this;
    }

    public void update(Point newLocation, String newDirection){
        pacManLocation = newLocation;
        pacManDirection = newDirection;
    }

}
