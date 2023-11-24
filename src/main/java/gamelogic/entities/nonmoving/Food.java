package gamelogic.entities.nonmoving;

import gamelogic.entities.Entity;
import gamelogic.entities.moving.PacMan;
import utility.ResourceHandler;

import java.awt.image.BufferedImage;
/**
 * This class represents a food entity
 */
public class Food extends Entity {
    /**
     * Constructor for the food entity
     * @param x The x coordinate of the food entity
     * @param y The y coordinate of the food entity
     */
    public Food(int x, int y){
        super(x ,y);
        setSprite("food");
    }

    /**
     * Gets the sprite of the food entity
     * @return The sprite of the food entity
     */
    public BufferedImage getSprite(){
        return ResourceHandler.getSpriteMap("edibles").get(spriteName);
    }

    /**
     * Checks if the food entity is edible
     * @return Always true
     */
    @Override
    public boolean isEdible(){
        return true;
    }

    /**
     * Eats the food entity
     * @param pacMan The PacMan that eats the food entity
     */
    @Override
    public void eatenBy(PacMan pacMan){
        int score = switch(spriteName){
            case "food" -> 10;
            case "power_pellet" -> 50;
            case "cherry" -> 100;
            default -> 0;
        };

        if(score == 50){
            pacMan.setCanEatGhosts(true);
        }
        if(!spriteName.equals("none") && (observer != null)){
                observer.addScore(score);
                observer.incrementEaten();
        }
        setSprite("none");
    }
}
