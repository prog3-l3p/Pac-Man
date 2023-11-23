package gamelogic.entities.nonmoving;

import gamelogic.entities.Entity;
import gamelogic.entities.moving.PacMan;
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
        if(observer != null){
            observer.addScore(score);
        }
        setSprite("none");
    }
}
