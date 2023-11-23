package gui.leveleditor;


import gamelogic.LevelData;
import gui.Main;
import gui.mainmenu.MainMenuFrame;
import utility.ResourceHandler;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.logging.Level;


/**
 * This class is responsible for everything concerning the level editor.
 * It is a JFrame that contains a EntityChooserPanel and an EditorViewPanel.
 * It also contains a save button and a load button.
 */
public class LevelEditorFrame extends JFrame {
    EntityChooserPanel entityChooserPanel = new EntityChooserPanel();
    EditorViewPanel viewPanel = new EditorViewPanel();
    private static String currentSprite;
    private static String currentEntityType;
    private static final String SAVE_LEVEL = "Save Level";
    private static final String LOAD_LEVEL = "Load Level";

    /**
     * Constructor for the level editor frame
     */
    public LevelEditorFrame(){
        setTitle("Level editor");
        setIconImage(ResourceHandler.getIcon("LevelEditorIcon"));
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        add(entityChooserPanel, BorderLayout.NORTH);
        add(viewPanel, BorderLayout.CENTER);

        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        JButton mainMenuButton = new JButton("Main menu");

        entityChooserPanel.add(saveButton);
        entityChooserPanel.add(loadButton);
        entityChooserPanel.add(mainMenuButton);

        loadButton.setBorderPainted(false);
        mainMenuButton.setBorderPainted(false);

        saveButton.addActionListener(e -> showDialog(SAVE_LEVEL));
        loadButton.addActionListener(e -> showDialog(LOAD_LEVEL));
        mainMenuButton.addActionListener(e -> Main.setDisplayedFrame(new MainMenuFrame()));
        pack();
    }

    /**
     * @return the current sprite that is selected in the level editor
     */
    public static String getCurrentSprite(){
        return currentSprite;
    }

    /**
     * @return the current entity type that is selected in the level editor
     */
    public static String getCurrentEntityType(){return currentEntityType;}

    /**
     * Set the current sprite that is selected in the level editor
     * @param spriteName the name of the sprite that is selected
     */
    public static void setCurrentSprite(String spriteName){
        currentSprite = spriteName;
    }

    /**
     * Set the current entity type that is selected in the level editor
     * @param entityType the type of the entity that is selected
     */
    public static void setCurrentEntityType(String entityType){currentEntityType = entityType;}

    /**
     * Show a dialog that allows the user to save or load a level
     * @param title the title of the dialog
     */
    private void showDialog(String title) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle(title);

        int userSelection = 0;

        switch(title) {
            case SAVE_LEVEL -> userSelection = fileChooser.showSaveDialog(this);
            case LOAD_LEVEL -> userSelection = fileChooser.showOpenDialog(this);
            default -> Main.logger.log(Level.SEVERE, "Unexpected error.");
        }

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            switch (title) {
                case SAVE_LEVEL -> {
                    if(validLevel())
                        ResourceHandler.saveLevel(selectedFile.getAbsolutePath(), viewPanel.getLevelData());
                    else
                        JOptionPane.showMessageDialog(this, "Invalid level. Please make sure that there is exactly one PacMan, one Blinky, one Pinky, one Inky, and one Clyde.");
                }
                case LOAD_LEVEL -> {
                    LevelData level =  ResourceHandler.loadLevel(selectedFile.getAbsolutePath());
                    viewPanel.loadEntities(level.getEntities(), level.getLocations());
                }
                default -> Main.logger.log(Level.WARNING, "Unexpected error while performing file operation.");
            }
        }
    }

    /**
     * Checks the levels validity
     * @return true if there are 4 ghosts and 1 PacMan, false otherwise
     */
    private boolean validLevel(){
        return viewPanel.getLevelData().getLocations().size() == 5;
    }
}