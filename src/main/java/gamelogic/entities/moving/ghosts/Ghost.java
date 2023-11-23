package gamelogic.entities.moving.ghosts;

import gamelogic.entities.Entity;
import gamelogic.entities.moving.MovingEntity;
import gamelogic.entities.moving.PacMan;

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
    public void eatenBy(PacMan pacMan){
    }

    @Override
    public boolean isTraversableByGhosts() {
        return false;
    }

    public void update(Point newLocation, String newDirection, boolean canEatGhosts){
        pacManLocation = newLocation;
        pacManDirection = newDirection;
        isFrightened = canEatGhosts;
    }




}
