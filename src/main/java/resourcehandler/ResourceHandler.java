package resourcehandler;

import gamelogic.Entity;
import gui.Main;

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
    private static HashSet<String> entityTypes = new HashSet<>();
    private static Font pacFont;
    private static ArrayList<ArrayList<Entity>> currentLevel = null;
    private static final float FONT_SIZE = 72F;


    public static void init(){
        initFont();
        initSpriteMaps();
    }

    public static HashMap<String, BufferedImage> getSpriteMap(String entityType){
        return upperMap.get(entityType);
    }

    public static Font getPacFont(){
        return pacFont;
    }

    public static Image getIcon(String iconName){
        File iconFile = new File("res/icons/"+iconName+".png");
        BufferedImage icon;
        try {
            icon = ImageIO.read(iconFile);
        } catch (IOException e) {
            Main.logger.log(Level.WARNING, "Could not load icon: " + iconName, e);
        }
        return icon;
    }

    public static HashSet<String> getEntityTypes(){
        return entityTypes;
    }

    private ResourceHandler(){}

    private static void initFont(){
        try {
            pacFont = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/emulogic.ttf")).deriveFont(FONT_SIZE);
        } catch (IOException | FontFormatException e) {
            Main.logger.log(Level.WARNING, "Could not load custom font!", e);
        }
    }

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
    // Recursively gets all the sprites from the given directory.

    // Save level to a file
    public static void saveLevel(String fileName, ArrayList<ArrayList<Entity>> level) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(level);
        } catch (Exception e) {
            Main.logger.log(Level.WARNING, "Error occurred while saving level: ", e);
        }
    }

    // Load level from a file
    public static ArrayList<ArrayList<Entity>> loadLevel(String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
           return (ArrayList<ArrayList<Entity>>) inputStream.readObject();
        } catch (Exception e) {
            Main.logger.log(Level.WARNING, "Error occurred while loading level: ", e);
        }
        return new ArrayList<>();
    }

    public static void levelSelectDialog(JComponent component){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select level");

        int userSelection = fileChooser.showOpenDialog(component);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            currentLevel = ResourceHandler.loadLevel(selectedFile.getAbsolutePath());
        }
    }

    public static ArrayList<ArrayList<Entity>> getCurrentLevel(){
        return currentLevel;
    }

}
