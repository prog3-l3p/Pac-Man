package gui.leveleditor;

import gamelogic.nonmoving.Wall;
import gui.Main;
import resourcehandler.ResourceHandler;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.logging.Level;


/**
 * This class is responsible for everything concerning the level editor.
 * It is a JFrame that contains a WallChooserPanel and an EditorViewPanel.
 * It also contains a save button and a load button.
 */
public class LevelEditorFrame extends JFrame {
    WallChooserPanel wallChooserPanel = new WallChooserPanel();
    EditorViewPanel viewPanel = new EditorViewPanel();
    private static String currentWallSprite = null;

    private static final String SAVE_LEVEL = "Save Level";
    private static final String LOAD_LEVEL = "Load Level";

    // Initialize the level editor panel
    public LevelEditorFrame(){
        setTitle("Level editor");
        setIconImage(ResourceHandler.getSprites().get("LevelEditorIcon.png"));
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        add(wallChooserPanel, BorderLayout.NORTH);
        add(viewPanel, BorderLayout.CENTER);

        JButton saveButton = new JButton("Save");
        wallChooserPanel.add(saveButton);
        JButton loadButton = new JButton("Load");
        wallChooserPanel.add(loadButton);

        saveButton.addActionListener(e -> showDialog(SAVE_LEVEL));

        loadButton.addActionListener(e -> showDialog(LOAD_LEVEL));
        pack();
    }

    public static String getCurrentWallSprite(){
        return currentWallSprite;
    }

    public static void setCurrentWallSprite(String spriteName){
        currentWallSprite = spriteName;
    }

    // Show a dialog to save or load the level
    private void showDialog(String title) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle(title);

        int userSelection = 0;

        switch(title) {
            case SAVE_LEVEL -> userSelection = fileChooser.showSaveDialog(this);
            case LOAD_LEVEL -> userSelection = fileChooser.showOpenDialog(this);
            default -> Main.logger.log(Level.WARNING, "Unexpected error.");
        }

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            switch (title) {
                case SAVE_LEVEL -> ResourceHandler.saveLevel(selectedFile.getAbsolutePath(), viewPanel.getWalls());
                case LOAD_LEVEL -> {
                    Wall[][] level = ResourceHandler.loadLevel(selectedFile.getAbsolutePath());
                    viewPanel.setWalls(level);
                }
                default -> Main.logger.log(Level.WARNING, "Unexpected error while performing file operation.");
            }
        }
    }
}