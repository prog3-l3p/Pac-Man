package gamelogic;

import gamelogic.entities.Entity;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is used to store a level's data
 */
public class LevelData implements Serializable {
    /**
     * Stores the entities in the level
     */
    private final ArrayList<ArrayList<Entity>> entities;
    /**
     * Stores the locations of the ghosts and PacMan
     */
    private final HashMap<String, Point> locations;
    /**
     * Stores the number of food in the level
     */
    private int foodCount = 0;

    /**
     * Constructor for the level data
     * @param entities The entities in the level
     * @param locations The locations of the ghosts and PacMan
     * @param foodCount The number of food in the level
     */
    public LevelData(ArrayList<ArrayList<Entity>> entities, HashMap<String, Point> locations, int foodCount) {
        this.entities = entities;
        this.locations = locations;
        this.foodCount = foodCount;
    }

    /**
     * @return The entities in the level
     */
    public ArrayList<ArrayList<Entity>> getEntities() {
        return entities;
    }

    /**
     * @return The locations of the ghosts and PacMan
     */
    public HashMap<String, Point> getLocations() {
        return locations;
    }

    /**
     * @return The number of food in the level
     */
    public int getFoodCount() {
        return foodCount;
    }
}
