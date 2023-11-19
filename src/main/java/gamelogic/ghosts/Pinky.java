package gamelogic.ghosts;

import gamelogic.Entity;

import java.awt.image.BufferedImage;

public class Pinky extends Entity  {
    public Pinky(int x, int y) {
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
