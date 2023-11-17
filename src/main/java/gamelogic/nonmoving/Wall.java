package gamelogic.nonmoving;

import gamelogic.Entity;
import gui.ApplicationFrame;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.HashMap;
/**
 * A wall in the game.
 */
public class Wall implements Entity, Serializable {
    private final int x;
    private final int y;
    private HashMap<String, String> spriteNames;
    private transient BufferedImage currentSprite;

    private String currentSpriteName = "empty";

    public Wall(int x, int y){
        getInterestingSprites();
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    // Gets all the wall sprites from the sprites HashMap in ApplicationFrame.
    private void getInterestingSprites() {
        spriteNames = new HashMap<>();
        for(String spritePath : ApplicationFrame.sprites.keySet()){
            if(spritePath.contains("walls")){
                String spriteShortHand = spritePath
                        .substring(spritePath.indexOf("walls") + 6, spritePath.indexOf(".png"));
                spriteNames.put(spriteShortHand, spritePath);
            }
        }
    }

    // Setters and getters for the current sprite.
    public void setSprite(String spriteName){
        currentSpriteName = spriteName;
    }

    public BufferedImage getCurrentSprite(){
        String spritePath = spriteNames.get(currentSpriteName);
        return ApplicationFrame.sprites.get(spritePath);
    }


}
