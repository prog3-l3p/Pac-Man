package gui.leveleditor;

import gamelogic.entities.Entity;
import gamelogic.entities.moving.ghosts.Blinky;
import gamelogic.entities.moving.ghosts.Clyde;
import gamelogic.entities.moving.ghosts.Inky;
import gamelogic.entities.moving.ghosts.Pinky;
import gamelogic.entities.nonmoving.Food;
import gamelogic.entities.nonmoving.Wall;
import gamelogic.entities.moving.PacMan;
import gamelogic.LevelData;
import utility.ResourceHandler;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import static utility.GameConstants.*;


/**
 *
 * This class is responsible for the visual representation of the level editor
 */
public class EditorViewPanel extends JPanel {
    private final ArrayList<ArrayList<Entity>> entities = new ArrayList<>();
    private final ArrayList<ArrayList<JLabel>> cells = new ArrayList<>();
    private HashMap<String, Point> locations = new HashMap<>();

    private boolean pacManPlaced = false;
    private boolean inkyPlaced = false;
    private boolean blinkyPlaced = false;
    private boolean pinkyPlaced = false;
    private boolean clydePlaced = false;
    
    /**
     * Constructor for the level editor view panel
     */
    public EditorViewPanel(){
        initEntities();
        initCells();
        setLayout(new GridLayout(ROW_COUNT, COLUMN_COUNT));
        setBackground(Color.BLACK);
    }

    /**
     * Initialize the JLabels that represent the cells of the level editor view panel
     */
    private void initCells(){
        Dimension cellSize = new Dimension(CELL_SIZE,CELL_SIZE);
        // Create a 31row*28column grid of JLabels
        for(int y = 0; y < ROW_COUNT; y++){
            ArrayList<JLabel> rowLabels = new ArrayList<>();
            for(int x = 0; x < COLUMN_COUNT; x++){
                BufferedImage foodSprite = ResourceHandler.getSpriteMap("edibles").get("food");
                JLabel cell = new JLabel();
                cell.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                cell.setPreferredSize(cellSize);
                cell.setMinimumSize(cellSize);
                cell.setMaximumSize(cellSize);
                cell.setIcon(new ImageIcon(foodSprite));
                int finalX = x;
                int finalY = y;
                addClickHandler(cell, finalY, finalX);
                rowLabels.add(finalX, cell);
                add(cell);
            }
            cells.add(y, rowLabels);
        }
    }

    /**
     * Adds a mouse listener to a cell
     * @param cell the cell to add the mouse listener to
     * @param finalY the y coordinate of the cell
     * @param finalX the x coordinate of the cell
     */
    private void addClickHandler(JLabel cell, int finalY, int finalX) {
        cell.addMouseListener(new MouseAdapter() {
            /**
             * Invoked when a cell has been clicked on.
             * @param e the event to be processed
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                Entity editedEntity = entities.get(finalY).get(finalX);
                if (LevelEditorFrame.getCurrentSprite() == null) {
                    return;
                }
                String currentEntityType = LevelEditorFrame.getCurrentEntityType();
                String currentSpriteName = LevelEditorFrame.getCurrentSprite();
                BufferedImage newIcon = ResourceHandler.getSpriteMap(currentEntityType).get(currentSpriteName);
                BufferedImage foodSprite = ResourceHandler.getSpriteMap("edibles").get("food");
                cell.setIcon(new ImageIcon(newIcon));
                // Create a new entity based on the current entity type
                // For moving entities, set their location in the locations hashmap
                switch (currentEntityType) {
                    case INKY -> {
                        if (inkyPlaced) {
                            int oldX = locations.get(INKY).x;
                            int oldY = locations.get(INKY).y;
                            entities.get(oldY).set(oldX, new Food(oldX, oldY));
                            cells.get(oldY).get(oldX).setIcon(new ImageIcon(foodSprite));
                        }
                        locations.put(INKY, new Point(finalX, finalY));
                        editedEntity = new Inky(finalX, finalY);
                        inkyPlaced = true;
                    }
                    case BLINKY -> {
                        if (blinkyPlaced) {
                            int oldX = locations.get(BLINKY).x;
                            int oldY = locations.get(BLINKY).y;
                            entities.get(oldY).set(oldX, new Food(oldX, oldY));
                            cells.get(oldY).get(oldX).setIcon(new ImageIcon(foodSprite));
                        }
                        locations.put(BLINKY, new Point(finalX, finalY));
                        editedEntity = new Blinky(finalX, finalY);
                        blinkyPlaced = true;
                    }
                    case CLYDE -> {
                        if (clydePlaced) {
                            int oldX = locations.get(CLYDE).x;
                            int oldY = locations.get(CLYDE).y;
                            entities.get(oldY).set(oldX, new Food(oldX, oldY));
                            cells.get(oldY).get(oldX).setIcon(new ImageIcon(foodSprite));
                        }
                        locations.put(CLYDE, new Point(finalX, finalY));
                        editedEntity = new Clyde(finalX, finalY);
                        clydePlaced = true;
                    }
                    case PINKY -> {
                        if (pinkyPlaced) {
                            int oldX = locations.get("pinky").x;
                            int oldY = locations.get("pinky").y;
                            entities.get(oldY).set(oldX, new Food(oldX, oldY));
                            cells.get(oldY).get(oldX).setIcon(new ImageIcon(foodSprite));
                        }
                        locations.put("pinky", new Point(finalX, finalY));
                        editedEntity = new Pinky(finalX, finalY);
                        pinkyPlaced = true;
                    }
                    case PACMAN -> {
                        if (pacManPlaced) {
                            int oldX = locations.get(PACMAN).x;
                            int oldY = locations.get(PACMAN).y;
                            entities.get(oldY).set(oldX, new Food(oldX, oldY));
                            cells.get(oldY).get(oldX).setIcon(new ImageIcon(foodSprite));
                        }
                        locations.put(PACMAN, new Point(finalX, finalY));
                        editedEntity = new PacMan(finalX, finalY);
                        pacManPlaced = true;
                    }
                    case "walls" -> {
                        editedEntity = new Wall(finalX, finalY);
                        editedEntity.setSprite(currentSpriteName);
                        if (!currentSpriteName.equals("empty")) {
                            if (!currentSpriteName.equals("pm-non-traverse")) {
                                editedEntity.setNotTraversableByGhosts();
                            }
                            editedEntity.setNotTraversableByPacMan();
                        }
                    }
                    case "edibles" -> {
                        editedEntity = new Food(finalX, finalY);
                        editedEntity.setSprite(currentSpriteName);
                    }
                }
                entities.get(finalY).set(finalX, editedEntity);
            }
        });
    }

    /**
     * Initializes the entity list with food entities
     */
    private void initEntities(){
        for(int y = 0; y < ROW_COUNT; y++){
            entities.add(new ArrayList<>());
            for(int x = 0; x < COLUMN_COUNT; x++){
                entities.get(y).add(new Food(x, y));
            }
        }
    }

    /**
     * @return an ArrayList of ArrayLists of entities
     */
    public LevelData getLevelData(){
        return new LevelData(entities, locations);
    }
    /**
     * Loads the level into the level editor
     * @param entities the entities to be loaded
     */
    public void loadEntities(ArrayList<ArrayList<Entity>> entities, HashMap<String, Point> locations) {
        this.locations = locations;
        this.entities.clear();
        for (int y = 0; y < ROW_COUNT; y++) {
            this.entities.add(new ArrayList<>());
            for (int x = 0; x < COLUMN_COUNT; x++) {
                this.entities.get(y).add(entities.get(y).get(x));
                BufferedImage entitySprite = entities.get(y).get(x).getSprite();
                BufferedImage emptyCell = ResourceHandler.getSpriteMap("walls").get("empty");
                BufferedImage pacManNonTraversableCell = ResourceHandler.getSpriteMap("walls").get("pm-non-traverse");
                BufferedImage ghostNonTraversableCell = ResourceHandler.getSpriteMap("walls").get("ghost-non-traverse");
                // check if the cell is a non-traversable empty cell
                if(entitySprite.equals(emptyCell) && !entities.get(y).get(x).isTraversableByGhosts()) {
                    cells.get(y).get(x).setIcon(new ImageIcon(ghostNonTraversableCell));
                } else if (entitySprite.equals(emptyCell) && !entities.get(y).get(x).isTraversableByPacMan()) {
                    cells.get(y).get(x).setIcon(new ImageIcon(pacManNonTraversableCell));
                } else {
                    cells.get(y).get(x).setIcon(new ImageIcon(entitySprite));
                }
            }
        }
    }
}
