package gamelogic.nonmoving;

import gamelogic.Entity;
import resourcehandler.ResourceHandler;

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
    private String currentSpriteName = "empty";
    boolean traversableByPacMan = true;
    boolean traversableByGhosts = true;

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

    @Override
    public int getWidth() {
        return getSprite().getWidth();
    }

    @Override
    public int getHeight() {
        return getSprite().getHeight();
    }

    // Gets all the wall sprites from the sprites HashMap in ApplicationFrame.
    private void getInterestingSprites() {
        spriteNames = new HashMap<>();
        for(String spritePath : ResourceHandler.getSprites().keySet()){
            if(spritePath.contains("walls")){
                String spriteShortHand = spritePath
                        .substring(spritePath.indexOf("walls") + 6, spritePath.indexOf(".png"));
                spriteNames.put(spriteShortHand, spritePath);
            }
        }
    }

    public HashMap<String, String> getSpriteNames(){
        return spriteNames;
    }

    public boolean isTraversableByPacMan(){
        return traversableByPacMan;
    }

    public boolean isTraversableByGhosts() {
        return traversableByGhosts;
    }

    public void setTraversableByPacMan(boolean b){
        traversableByPacMan = b;
    }

    public void setTraversableByGhosts(boolean b){
        traversableByGhosts = b;
    }

    // Setters and getters for the current sprite.
    public void setSprite(String spriteName){
        currentSpriteName = spriteName;
    }

    public BufferedImage getSprite(){
        String spritePath = spriteNames.get(currentSpriteName);
        return ResourceHandler.getSprites().get(spritePath);
    }


}
