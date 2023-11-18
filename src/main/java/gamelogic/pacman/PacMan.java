package gamelogic.pacman;

import gamelogic.Entity;
import resourcehandler.ResourceHandler;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * PacMan in the game.
 */
public class PacMan implements Entity {
    private HashMap<String, BufferedImage> sprites;
    private BufferedImage currentSprite;
    private String currentDirection = "neutral";
    private int speedX;
    private int speedY;
    private int x;
    private int y;

    public PacMan(int x, int y) {
        this.x = x;
        this.y = y;
        getInterestingSprites();
        currentSprite = sprites.get("neutral");
    }
    // Gets all the PacMan sprites from the sprites HashMap in ApplicationFrame.
    private void getInterestingSprites() {
        sprites = new HashMap<>();
        for(String spritePath : ResourceHandler.getSprites().keySet()){
            if(spritePath.contains("pacman")){
                String spriteShortHand = spritePath
                        .substring(spritePath.indexOf("pacman") + 7, spritePath.indexOf(".png"));
                sprites.put(spriteShortHand, ResourceHandler.getSprites().get(spritePath));
            }
        }
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public void move(){
        if(x + speedX >= 0 && x + speedX <= 28*22)
            x += speedX;
        if(y + speedY >= 0 && y + speedY <= 31*22)
            y += speedY;
    }

    public void menuMove(int screenWidth){
        currentDirection = "right";
        if(x + 22 <= screenWidth) x += 22;
        else x = 0;
    }

    // Changes the current sprite to the next sprite in the animation.
    public BufferedImage getCurrentSprite(){
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

    // Changes the speed of PacMan based on the key pressed.
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch(key){
            case KeyEvent.VK_LEFT -> {
                currentDirection = "left";
                speedX = -22;
                speedY = 0;
            }
            case KeyEvent.VK_RIGHT -> {
                currentDirection = "right";
                speedX = 22;
                speedY = 0;
            }
            case KeyEvent.VK_UP ->  {
                currentDirection = "up";
                speedY = -22;
                speedX = 0;
            }
            case KeyEvent.VK_DOWN -> {
                currentDirection = "down";
                speedY = 22;
                speedX = 0;
            }
        }
    }

}