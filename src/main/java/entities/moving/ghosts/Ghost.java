package entities.moving.ghosts;

import entities.Entity;
import entities.moving.MovingEntity;
import entities.moving.PacMan;

import java.awt.image.BufferedImage;

public abstract class Ghost extends MovingEntity {
    PacManObserver pacManObserver = new PacManObserver();
    protected boolean isFrightened;
    protected boolean isDead;
    protected Ghost(int x, int y) {
        super(x, y);
    }
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

    public void pacManObserverAdd(PacMan p){
        pacManObserver.setPacMan(p);
    }
}
