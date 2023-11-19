package gamelogic.pacman;

import gamelogic.Entity;
import resourcehandler.ResourceHandler;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.HashMap;

/**
 * PacMan in the game.
 */
public class PacMan extends Entity implements Serializable {
    private transient HashMap<String, BufferedImage> sprites;
    private transient BufferedImage currentSprite;
    private String currentDirection = "neutral";
    private int speedX;
    private int speedY;

    public PacMan(int x, int y) {
        super(x ,y);
        sprites = ResourceHandler.getSpriteMap("pacman");
        currentSprite = sprites.get("neutral");
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public int getWidth(){
        return getSprite().getWidth();
    }
    public int getHeight(){
        return getSprite().getHeight();
    }

    public void move(){
        if(x <= 0) {x += 28;}
        if(y <= 0) {y += 31;}
        if(ResourceHandler.getCurrentLevel().get((x + speedX) % 28).get((y + speedY) % 31).isTraversableByPacMan()) {
            x = (x + speedX) % 28;
            y = (y + speedY) % 31;
        }
        if(ResourceHandler.getCurrentLevel().get(x).get(y) != null){
            ResourceHandler.getCurrentLevel().get(x).get(y).eat();
        }
    }

    public void menuMove(int screenWidth){
        currentDirection = "right";
        if(x + 1 <= screenWidth) x += 1;
        else x = 0;
        currentSprite = getSprite();
    }

    // Gets the current sprite of PacMan and if needed changes it to the next one appropriate
    public BufferedImage getSprite(){
        switch(currentDirection){
            case "left" -> {
                if(currentSprite == sprites.get("left_1")){
                    currentSprite = sprites.get("left_2");
                } else {
                    currentSprite = sprites.get("left_1");
                }
            }
            case "right" -> {
                if(currentSprite == sprites.get("right_1")){
                    currentSprite = sprites.get("right_2");
                } else {
                    currentSprite = sprites.get("right_1");
                }
            }
            case "up" -> {
                if(currentSprite == sprites.get("up_1")){
                    currentSprite = sprites.get("up_2");
                } else {
                    currentSprite = sprites.get("up_1");
                }
            }
            case "down" -> {
                if(currentSprite == sprites.get("down_1")){
                    currentSprite = sprites.get("down_2");
                } else {
                    currentSprite = sprites.get("down_1");
                }
            }
        }

        return currentSprite;
    }

    @Override
    public void setSprite(String spriteName) {
        super.setSprite(spriteName);
        currentSprite = sprites.get(spriteName);
    }

    @Override
    public boolean isTraversableByPacMan() {
        return false;
    }

    // Changes the speed of PacMan based on the key pressed.
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch(key){
            case KeyEvent.VK_LEFT -> {
                currentDirection = "left";
                speedX = -1;
                speedY = 0;
            }
            case KeyEvent.VK_RIGHT -> {
                currentDirection = "right";
                speedX = 1;
                speedY = 0;
            }
            case KeyEvent.VK_UP ->  {
                currentDirection = "up";
                speedY = -1;
                speedX = 0;
            }
            case KeyEvent.VK_DOWN -> {
                currentDirection = "down";
                speedY = 1;
                speedX = 0;
            }
        }
    }

}