package gamelogic.nonmoving;

import gamelogic.Entity;
import resourcehandler.ResourceHandler;

import java.awt.image.BufferedImage;
import java.util.*;

public class Food implements Entity {
    private HashMap<String, BufferedImage> sprites;
    private BufferedImage sprite;

    private final Random random = new Random();
    private int x;
    private int y;

    public Food(int x, int y){
        this.x = x;
        this.y = y;
        getInterestingSprites();
        setSprite("food");
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getWidth(){
        return sprite.getWidth();
    }

    public int getHeight(){
        return sprite.getHeight();
    }

    public BufferedImage getSprite(){
        return sprite;
    }

    private void getInterestingSprites() {
        sprites = new HashMap<>();
        for(String spritePath : ResourceHandler.getSprites().keySet()){
            if(spritePath.contains("edibles")){
                String spriteShortHand = spritePath
                        .substring(spritePath.indexOf("edibles") + 8, spritePath.indexOf(".png"));
                sprites.put(spriteShortHand, ResourceHandler.getSprites().get(spritePath));
            }
        }
    }

    private BufferedImage getRandomSprite(){
        int randomSpriteIndex = random.nextInt(8);
        ArrayList<BufferedImage> spriteList = new ArrayList<>(sprites.values());
        return spriteList.get(randomSpriteIndex);
    }

    public void setSprite(String name){
        sprite = sprites.get(name);
    }

}
