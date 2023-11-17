package gamelogic.pacman;

import gamelogic.Entity;
import gui.ApplicationFrame;

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
    private int x = 28*11;
    private int y = 31*11;

    public PacMan() {
        getInterestingSprites();
        currentSprite = sprites.get("neutral");
    }
    // Gets all the PacMan sprites from the sprites HashMap in ApplicationFrame.
    private void getInterestingSprites() {
        sprites = new HashMap<>();
        for(String spritePath : ApplicationFrame.sprites.keySet()){
            if(spritePath.contains("pacman")){
                String spriteShortHand = spritePath
                        .substring(spritePath.indexOf("pacman") + 7, spritePath.indexOf(".png"));
                sprites.put(spriteShortHand, ApplicationFrame.sprites.get(spritePath));
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