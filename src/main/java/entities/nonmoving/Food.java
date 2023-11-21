package entities.nonmoving;

import entities.Entity;
import utility.ResourceHandler;

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
    public boolean isEdible(){
        return true;
    }

    @Override
    public void eatenBy(Entity e){
        setSprite("none");
    }
}
