package gamelogic.pacman;

import gamelogic.Entity;

import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

public class PacMan implements Entity {
    private HashMap<String,BufferedImage> sprites = null;
    private BufferedImage currentSprite;
    private String currentDirection = "neutral";
    private int speedX;
    private int speedY;
    private int x = 400;
    private int y = 300;

    public PacMan() {
        initSprites();
        currentSprite = sprites.get("neutral");
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public void move(){
        if(x + speedX >= 0 && x + speedX <= 800)
            x += speedX;
        if(y + speedY >= 0 && y + speedY <= 600)
            y += speedY;
    }

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


    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch(key){
            case KeyEvent.VK_LEFT -> {
                currentDirection = "left";
                speedX = -800/28;
                speedY = 0;
            }
            case KeyEvent.VK_RIGHT -> {
                currentDirection = "right";
                speedX = 800/28;
                speedY = 0;
            }
            case KeyEvent.VK_UP ->  {
                currentDirection = "up";
                speedY = -600/31;
                speedX = 0;
            }
            case KeyEvent.VK_DOWN -> {
                currentDirection = "down";
                speedY = 600/31;
                speedX = 0;
            }
        }
    }

    /**
     *
     */
    private void initSprites(){
        try {
            sprites = new HashMap<>();
            sprites.put("neutral", ImageIO.read(new File("res/sprites/pacman/neutral.png")));
            sprites.put("right_1", ImageIO.read(new File("res/sprites/pacman/right_1.png")));
            sprites.put("right_2", ImageIO.read(new File("res/sprites/pacman/right_2.png")));
            sprites.put("left_1", ImageIO.read(new File("res/sprites/pacman/left_1.png")));
            sprites.put("left_2", ImageIO.read(new File("res/sprites/pacman/left_2.png")));
            sprites.put("up_1", ImageIO.read(new File("res/sprites/pacman/up_1.png")));
            sprites.put("up_2", ImageIO.read(new File("res/sprites/pacman/up_2.png")));
            sprites.put("down_1", ImageIO.read(new File("res/sprites/pacman/down_1.png")));
            sprites.put("down_2", ImageIO.read(new File("res/sprites/pacman/down_2.png")));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}