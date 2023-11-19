package gamelogic.nonmoving;

import gamelogic.Entity;
import resourcehandler.ResourceHandler;

import java.awt.image.BufferedImage;
/**
 * This class represents a food entity
 */
public class Food extends Entity {
    public Food(int x, int y){
        super(x ,y);
        setSprite("food");
    }

    public BufferedImage getSprite(){
        return ResourceHandler.getSpriteMap("edibles").get(spriteName);
    }

    @Override
    public boolean isTraversableByPacMan() {
        return true;
    }

    @Override
    public void eat(){
        setSprite("none");
    }
}
