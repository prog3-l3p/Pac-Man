package gamelogic;

/**
 * This class is used to observe the state of the game
 */
public class EntityObserver {
    private int score;
    private int foodCount;
    private int eatenCount;

    /**
     * Constructor for the entity observer
     */
    public EntityObserver() {
        this.score = 0;
    }

    /**
     * Adds the given score to the current score
     * @param score The score to be added
     */
    public void addScore(int score){
        this.score += score;
    }

    /**
     * @return The current score
     */
    public int getScore(){
        return score;
    }

    /**
     * Sets the food count
     * @param foodCount The food count
     */
    public void setFoodCount(int foodCount) {
        this.foodCount = foodCount;
    }

    /**
     * @return The food count
     */
    public int getFoodCount() {
        return foodCount - eatenCount;
    }

    /**
     * Increments the number of eaten food
     */
    public void incrementEaten() {
        eatenCount++;
    }

    /**
     * Determines whether Blinky should leave home
     * @return True if more than 30 food has been eaten, false otherwise
     */
    public boolean shouldInkyLeaveHome() {
        return eatenCount >= 30;
    }

    /**
     * Determines whether Pinky should leave home
     * @return True if more than a third of the food has been eaten, false otherwise
     */
    public boolean shouldClydeLeaveHome() {
        return eatenCount >= foodCount/3;
    }


}
