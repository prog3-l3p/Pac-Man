package gamelogic.ghosts;

import gamelogic.Entity;

import java.awt.image.BufferedImage;

public class Clyde extends Entity {
    public Clyde(int x, int y) {
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
