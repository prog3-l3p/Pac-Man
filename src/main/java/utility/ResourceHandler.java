package utility;

import gamelogic.entities.Entity;
import gamelogic.LevelData;
import gui.Main;
import gui.mainmenu.MainMenuFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;

/**
 * This class handles the usage of resources (sprites, audio, etc.)
 */
public final class ResourceHandler {

    private static HashMap<String, HashMap<String, BufferedImage>> upperMap;
    private static final HashSet<String> entityTypes = new HashSet<>();
    private static Font pacFont;
    private static LevelData currentLevel = null;
    private static final float FONT_SIZE = 72F;

    /**
     * Initializes the resources
     */
    public static void init(){
        initFont();
        initSpriteMaps();
    }

    /**
     * @param entityType The type of entity
     * @return The sprite map for the given entity type
     */
    public static HashMap<String, BufferedImage> getSpriteMap(String entityType){
        return upperMap.get(entityType);
    }

    /**
     * @return the custom font used by the application
     */
    public static Font getPacFont(){
        return pacFont;
    }

    /**
     * @param iconName The name of the icon
     * @return The icon with the given name
     */
    public static Image getIcon(String iconName){
        File iconFile = new File("res/icons/"+iconName+".png");
        BufferedImage icon = null;
        try {
            icon = ImageIO.read(iconFile);
        } catch (IOException e) {
            Main.logger.log(Level.WARNING, "Could not load icon: " + iconName, e);
        }
        return icon;
    }

    /**
     * @return The set of entity types
     */
    public static HashSet<String> getEntityTypes(){
        return entityTypes;
    }

    /**
     * Save level to a file
     * @param fileName the name of the file
     * @param levelData the level to be saved
     */
    public static void saveLevel(String fileName, LevelData levelData) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(levelData);
        } catch (Exception e) {
            Main.logger.log(Level.SEVERE, "Error occurred while saving level: ", e);
        }
    }


    /**
     * Load level from a file
     * @param fileName the name of the file
     * @return the level that was loaded
     */
    public static LevelData loadLevel(String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
           return (LevelData) inputStream.readObject();
        } catch (Exception e) {
            Main.logger.log(Level.SEVERE, "Error occurred while loading level: ", e);
            Main.setDisplayedFrame(new MainMenuFrame());
        }
        return new LevelData(new ArrayList<>(), new HashMap<>(), 0);
    }

    /**
     * Show a dialog that allows the user to select a level
     * @param component the component that the dialog is displayed on
     */
    public static void levelSelectDialog(JComponent component){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select level");

        int userSelection = fileChooser.showOpenDialog(component);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            currentLevel = ResourceHandler.loadLevel(selectedFile.getAbsolutePath());
        }
    }

    /**
     * @return the current level
     */
    public static ArrayList<ArrayList<Entity>> getLevelEntities(){
       if(currentLevel != null)
            return currentLevel.getEntities();
       return null;
    }

    public static void setCurrentLevel(LevelData levelData) {
        currentLevel = levelData;
    }

    /**
     * @return the initial locations of the ghosts and pacman in the current level
     */
    public static HashMap<String, Point> getInitialLocations(){
        if(currentLevel == null)
            return null;
        return currentLevel.getLocations();
    }

    /**
     * @return the number of food entities in the current level
     */
    public static int getFoodCount() {
        if(currentLevel == null){
            return 0;
        }
        return currentLevel.getFoodCount();
    }

    /**
     * ResourceHandler is a utility class, so it should not be instantiated.
     */
    private ResourceHandler(){}

    /**
     * Initializes the custom font used by the application
     */
    private static void initFont(){
        try {
            pacFont = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/emulogic.ttf")).deriveFont(FONT_SIZE);
        } catch (IOException | FontFormatException e) {
            pacFont = new Font("Arial", Font.PLAIN, 12);
            Main.logger.log(Level.WARNING, "Could not load custom font!", e);
        }
    }

    /**
     * Initializes the sprite maps
     */
    private static void initSpriteMaps() {
        upperMap = new HashMap<>();

        File spriteDir = new File(System.getProperty("user.dir"), "res/sprites");
        File[] subDirs = spriteDir.listFiles();

        if(subDirs == null)
            return;

        for(File subDir : subDirs){
            if(!subDir.isDirectory())
                continue;
            HashMap<String, BufferedImage> spriteMap = new HashMap<>();
            for(File sprite : subDir.listFiles()){
                String spriteName = sprite.getName().split("\\.")[0];
                try {
                    spriteMap.put(spriteName, ImageIO.read(sprite));
                } catch (IOException e) {
                    Main.logger.log(Level.SEVERE, "Could not load sprites!", e);
                }
            }
            upperMap.put(subDir.getName(), spriteMap);
            entityTypes.add(subDir.getName());
        }
    }

    public static void resetLevel() {
        currentLevel = null;

    }
}
