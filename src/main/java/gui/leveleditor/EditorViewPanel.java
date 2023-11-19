package gui.leveleditor;

import gamelogic.Entity;
import gamelogic.ghosts.Blinky;
import gamelogic.ghosts.Clyde;
import gamelogic.ghosts.Inky;
import gamelogic.ghosts.Pinky;
import gamelogic.nonmoving.Food;
import gamelogic.nonmoving.Wall;
import gamelogic.pacman.PacMan;
import resourcehandler.ResourceHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


/**
 *
 * This class is responsible for the visual representation of the level editor
 */
public class EditorViewPanel extends JPanel {
    private final ArrayList<ArrayList<Entity>> entities = new ArrayList<>();
    private final ArrayList<ArrayList<JLabel>> cells = new ArrayList<>();
    private boolean pacManPlaced = false;
    private boolean inkyPlaced = false;
    private boolean blinkyPlaced = false;
    private boolean pinkyPlaced = false;
    private boolean clydePlaced = false;
    private static final int COLUMN_COUNT = 28;
    private static final int ROW_COUNT = 31;
    private static final int CELL_SIZE = 22;

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
                JLabel cell = new JLabel();
                cell.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                cell.setPreferredSize(cellSize);
                cell.setMinimumSize(cellSize);
                cell.setMaximumSize(cellSize);
                int finalX = x;
                int finalY = y;
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
                        cell.setIcon(new ImageIcon(newIcon));
                        // Create a new entity based on the current entity type
                        switch (currentEntityType) {
                            case "inky" -> {
                                if (!inkyPlaced)
                                    editedEntity = new Inky(finalX, finalY);
                                inkyPlaced = true;
                            }
                            case "blinky" -> {
                                if (!blinkyPlaced)
                                    editedEntity = new Blinky(finalX, finalY);
                                blinkyPlaced = true;
                            }
                            case "walls" -> {
                                editedEntity = new Wall(finalX, finalY);
                                editedEntity.setSprite(currentSpriteName);
                                if(!currentSpriteName.equals("empty")){
                                    editedEntity.setNotTraversableByPacMan();
                                }
                            }
                            case "clyde" -> {
                                if (!clydePlaced)
                                    editedEntity = new Clyde(finalX, finalY);
                                clydePlaced = true;
                            }
                            case "pinky" -> {
                                if (!pinkyPlaced)
                                    editedEntity = new Pinky(finalX, finalY);
                                pinkyPlaced = true;
                            }
                            case "pacman" -> {
                                if (!pacManPlaced)
                                    editedEntity = new PacMan(finalX, finalY);
                            }
                        }
                        entities.get(finalY).set(finalX, editedEntity);
                    }
                });
                rowLabels.add(finalX, cell);
                add(cell);
            }
            cells.add(y, rowLabels);
        }
    }

    /**
     * Initializes the entity list with food entities
     */
    private void initEntities(){
        for(int y = 0; y < ROW_COUNT; y++){
            entities.add(new ArrayList<>());
            for(int x = 0; x < COLUMN_COUNT; x++){
                entities.get(y).add(x, new Food(x, y));
            }
        }
    }

    /**
     * @return an ArrayList of ArrayLists of entities
     */
    public ArrayList<ArrayList<Entity>> getEntities(){
        return entities;
    }

    /**
     * Loads the level into the level editor
     * @param entities the entities to be loaded
     */
    public void loadEntities(ArrayList<ArrayList<Entity>> entities) {
        for (int y = 0; y < ROW_COUNT; y++) {
            this.entities.add(new ArrayList<>());
            for (int x = 0; x < COLUMN_COUNT; x++) {
                this.entities.get(y).set(x, entities.get(y).get(x));
                BufferedImage entitySprite = entities.get(y).get(x).getSprite();
                BufferedImage emptyCell = ResourceHandler.getSpriteMap("walls").get("empty");
                BufferedImage nonTraversableCell = ResourceHandler.getSpriteMap("walls").get("pm-non-traverse");
                // check if the cell is a non-traversable empty cell
                if(entitySprite.equals(emptyCell) && !entities.get(y).get(x).isTraversableByPacMan()) {
                    cells.get(y).get(x).setIcon(new ImageIcon(nonTraversableCell));
                } else {
                    cells.get(y).get(x).setIcon(new ImageIcon(entitySprite));
                }
            }
        }
    }
}
