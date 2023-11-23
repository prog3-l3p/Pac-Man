package gamelogic.entities.nonmoving;

import gamelogic.entities.Entity;
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
        if(spriteName.equals("pm-non-traverse") || spriteName.equals("ghost-non-traverse") || spriteName.equals("empty"))
            return ResourceHandler.getSpriteMap("walls").get("empty");
        else
            return ResourceHandler.getSpriteMap("walls").get(spriteName);
    }


}
