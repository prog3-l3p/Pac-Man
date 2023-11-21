package entities.moving.ghosts;

import entities.Entity;

import java.awt.image.BufferedImage;

public class Inky extends Entity  {
    public Inky(int x, int y) {
        super(x, y);
    }

    @Override
    public BufferedImage getSprite() {
        return null;
    }

    @Override
    public boolean isTraversableByPacMan() {
        return false;
    }
}