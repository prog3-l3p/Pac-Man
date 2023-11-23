package gamelogic.entities.nonmoving;

import gamelogic.entities.Entity;
import utility.ResourceHandler;

import java.awt.image.BufferedImage;

/**
 * This class represents a wall entity
 */
public class Wall extends Entity {
    /**
     * Constructor for the wall entity
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public Wall(int x, int y){
        super(x, y);
        setSprite("empty");
    }

    /**
     * Gets the sprite of the wall entity
     * @return The sprite of the wall entity
     */
    @Override
    public BufferedImage getSprite(){
        // If the sprite is a special non-traversable wall or just empty, return the empty sprite
        if(spriteName.equals("pm-non-traverse") || spriteName.equals("ghost-non-traverse") || spriteName.equals("empty"))
            return ResourceHandler.getSpriteMap("walls").get("empty");
        // Otherwise, return the normal sprite
        else
            return ResourceHandler.getSpriteMap("walls").get(spriteName);
    }

    /**
     * @return True if "empty" wall, false otherwise
     */
    @Override
    public boolean isTraversableByPacMan() {
        return spriteName.equals("empty");
    }

    /**
     * @return True if "empty" or PacMan non-traversable wall, false otherwise
     */
    @Override
    public boolean isTraversableByGhosts() {
        return spriteName.equals("empty") || spriteName.equals("pm-non-traverse");
    }


}
