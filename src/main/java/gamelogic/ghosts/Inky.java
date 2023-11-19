package gamelogic.ghosts;

import gamelogic.Entity;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Inky extends Entity implements Serializable {
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
