package entities.moving.ghosts;

import utility.ResourceHandler;

import java.awt.image.BufferedImage;

public class Clyde extends Ghost {
    public Clyde(int x, int y) {
        super(x, y);
    }

    @Override
    public BufferedImage getSprite() {
        getNextAnimation();
        return ResourceHandler.getSpriteMap("clyde").get(spriteName);
    }

    @Override
    public boolean isTraversableByPacMan() {
        return true;
    }
}
