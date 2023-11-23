package gamelogic;

import gamelogic.entities.Entity;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class LevelData implements Serializable {
    private final ArrayList<ArrayList<Entity>> entities;
    private final HashMap<String, Point> locations;

    public LevelData(ArrayList<ArrayList<Entity>> entities, HashMap<String, Point> locations) {
        this.entities = entities;
        this.locations = locations;
    }

    public ArrayList<ArrayList<Entity>> getEntities() {
        return entities;
    }

    public HashMap<String, Point> getLocations() {
        return locations;
    }
}
