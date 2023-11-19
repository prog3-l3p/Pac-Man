package gamelogic.nonmoving;

import gamelogic.Entity;
import resourcehandler.ResourceHandler;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.*;

public class Food extends Entity {
    private transient HashMap<String, BufferedImage> sprites;
/*    private String spriteName;*/
    private final Random random = new Random();

    public Food(){
        super(0,0);
    }

    public Food(int x, int y){
        super(x ,y);
        sprites = ResourceHandler.getSpriteMap("edibles");
        setSprite("food");
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public BufferedImage getSprite(){
        return ResourceHandler.getSpriteMap("edibles").get(spriteName);
    }

    private BufferedImage getRandomSprite(){
        int randomSpriteIndex = random.nextInt(8);
        ArrayList<BufferedImage> spriteList = new ArrayList<>(sprites.values());
        return spriteList.get(randomSpriteIndex);
    }

/*    public void setSprite(String spriteName){
        super.setSprite(spriteName);
        this.spriteName = spriteName;
    }*/


    @Override
    public boolean isTraversableByPacMan() {
        return true;
    }


    @Override
    public void eat(){
        setSprite("none");
    }
}
