package entities.nonmoving;

import entities.Entity;
import utility.ResourceHandler;

import java.awt.image.BufferedImage;

/**
 * This class represents a wall entity
 */
public class Wall extends Entity {
    public Wall(int x, int y){
        super(x, y);
        setSprite("empty");
    }

    @Override
    public BufferedImage getSprite(){
        if(!spriteName.equals("pm-non-traverse"))
            return ResourceHandler.getSpriteMap("walls").get(spriteName);
        else
            return ResourceHandler.getSpriteMap("walls").get("empty");
    }


}
