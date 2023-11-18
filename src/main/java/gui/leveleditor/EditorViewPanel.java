package gui.leveleditor;

import gamelogic.nonmoving.Wall;
import resourcehandler.ResourceHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * This class is responsible for displaying the level in the level editor.
 * It is a 31*28 grid of JLabels, each of which can be clicked to change the wall sprite.
 * The level is represented by a 2D array of Wall objects.
 * The level can be saved to a file and loaded from a file.
 */
public class EditorViewPanel extends JPanel {
    private final Wall[][] walls = new Wall[31][28];
    private final JLabel[][] wallCells = new JLabel[31][28];

    // Initialize the level editor view panel
    public EditorViewPanel(){
        initWalls();
        initCells();
        setLayout(new GridLayout(31, 28));
        setBackground(Color.BLACK);
    }
    // Initialize the cells of the level editor view panel
    private void initCells(){
        // Create 28*31 cells for the level
        for(int i = 0; i < 31; i++){
            for(int j = 0; j < 28; j++){
                wallCells[i][j] = new JLabel();
                wallCells[i][j].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                wallCells[i][j].setPreferredSize(new Dimension(22,22));
                wallCells[i][j].setMinimumSize(new Dimension(22,22));
                wallCells[i][j].setMaximumSize(new Dimension(22,22));
                int finalI = i;
                int finalJ = j;
                wallCells[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int col = wallCells[finalI][finalJ].getX() / wallCells[finalI][finalJ].getWidth();
                        int row = wallCells[finalI][finalJ].getY() / wallCells[finalI][finalJ].getWidth();
                        Wall editedWall = walls[row][col];
                        if(LevelEditorFrame.getCurrentWallSprite() != null){
                            // Terrible
                            BufferedImage newIcon = ResourceHandler.getSprites()
                                    .get(editedWall.getSpriteNames()
                                            .get(LevelEditorFrame.getCurrentWallSprite()));
                            if(LevelEditorFrame.getCurrentWallSprite().equals("pm-non-traverse")){
                                editedWall.setSprite("empty");
                                editedWall.setTraversableByPacMan(false);
                            }else if(!LevelEditorFrame.getCurrentWallSprite().equals("empty")){
                                editedWall.setSprite(LevelEditorFrame.getCurrentWallSprite());
                                editedWall.setTraversableByPacMan(false);
                                editedWall.setTraversableByGhosts(false);
                            }else{
                                editedWall.setSprite(LevelEditorFrame.getCurrentWallSprite());
                            }
                            wallCells[row][col].setIcon(new ImageIcon(newIcon));
                        }
                    }
                });
                add(wallCells[i][j]);
            }
        }
    }
    // Initialize the walls of the level editor view panel
    private void initWalls(){
        for(int row = 0; row < 31; row++){
            for(int col = 0; col < 28; col++){
                walls[row][col] = new Wall(col, row);
            }
        }
    }

    // Getters and setters
    public Wall[][] getWalls(){
        return walls;
    }

    public void setWalls(Wall[][] walls){
        for(int row = 0; row < 31; row++){
            for(int col = 0; col < 28; col++){
                this.walls[row][col] = walls[row][col];
                this.wallCells[row][col].setIcon(new ImageIcon(walls[row][col].getSprite()));
            }
        }
    }
}
