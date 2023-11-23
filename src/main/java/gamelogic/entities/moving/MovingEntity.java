package gamelogic.entities.moving;

import gamelogic.entities.Entity;

/**
 * Abstract class for all moving entities in the game.
 */
public abstract class MovingEntity extends Entity {
    protected int speedX;
    protected int speedY;
    protected static final String NEUTRAL = "neutral";
    protected static final String LEFT_1 = "left_1";
    protected static final String LEFT_2 = "left_2";
    protected static final String RIGHT_1 = "right_1";
    protected static final String RIGHT_2 = "right_2";
    protected static final String UP_1 = "up_1";
    protected static final String UP_2 = "up_2";
    protected static final String DOWN_1 = "down_1";
    protected static final String DOWN_2 = "down_2";
    protected String currentDirection = NEUTRAL;

    /**
     * Constructor for the moving entity.
     * @param x The x coordinate of the entity.
     * @param y The y coordinate of the entity.
     */
    protected MovingEntity(int x, int y) {
        super(x, y);
        setSprite(NEUTRAL);
    }

    /**
     * Sets the sprite to the next one in the animation.
     */
    protected void getNextAnimation() {
        switch(currentDirection){
            case "left" -> {
                if(spriteName.equals(LEFT_1)){
                    setSprite(LEFT_2);
                } else {
                    setSprite(LEFT_1);
                }
            }
            case "right" -> {
                if(spriteName.equals(RIGHT_1)){
                    setSprite(RIGHT_2);
                } else {
                    setSprite(RIGHT_1);
                }
            }
            case "up" -> {
                if(spriteName.equals(UP_1)){
                    setSprite(UP_2);
                } else {
                    setSprite(UP_1);
                }
            }
            case "down" -> {
                if(spriteName.equals(DOWN_1)){
                    setSprite(DOWN_2);
                } else {
                    setSprite(DOWN_1);
                }
            }
        }
    }

}
