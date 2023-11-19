package gamelogic.ghosts;

import gamelogic.Entity;

import java.awt.image.BufferedImage;

public class Blinky extends Entity{

    public Blinky(int x, int y) {
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
