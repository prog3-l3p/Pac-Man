package resourcehandler;

import gamelogic.nonmoving.Wall;
import gui.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.logging.Level;

/**
 * This class handles the usage of resources (sprites, audio, etc.)
 */
public class ResourceHandler {
    private static final HashMap<String, BufferedImage> sprites = new HashMap<>();
    private static Font pacFont;

    public void init(){
        initSprites();
        initFont();
    }

    // Loads all the sprites from the res/sprites directory.
    private static void initSprites(){
        File workingDirectory = new File(System.getProperty("user.dir"));
        File spriteDirectory = new File(workingDirectory, "res/sprites");
        gatherAllSprites(spriteDirectory);
    }

    // Loads the custom font.
    private static void initFont(){
        try {
            pacFont = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/emulogic.ttf")).deriveFont(72f);
        } catch (Exception e) {
            Main.logger.log(Level.WARNING, "Could not load custom font!");
        }
    }

    // Recursively gets all the sprites from the given directory.
    private static void gatherAllSprites(File dir) {
        try {
            File[] subdirs = dir.listFiles();
            if(subdirs == null){
                Main.logger.log(Level.SEVERE, "Sprites not found!");
            }
            for (File file : subdirs) {
                if (file.isDirectory()) {
                    gatherAllSprites(file);
                } else {
                    int neededIndex = file.getPath().indexOf("sprites") + 8;
                    String spriteName = file.getPath().substring(neededIndex);
                    sprites.put(spriteName, ImageIO.read(file));
                }
            }
        } catch (Exception e) {
            Main.logger.log(Level.WARNING, e.getMessage());
        }
    }
    // Getters
    public static HashMap<String, BufferedImage> getSprites() {
        return sprites;
    }
    public static Font getPacFont(){
        return pacFont;
    }

    // Save level to a file
    public static void saveLevel(String fileName, Wall[][] level) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(level);
        } catch (Exception e) {
            Main.logger.log(Level.WARNING, "Error occurred while saving level: " + e.getMessage());
        }
    }

    // Load level from a file
    public static Wall[][] loadLevel(String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
           return (Wall[][]) inputStream.readObject();
        } catch (Exception e) {
            Main.logger.log(Level.WARNING, "Error occurred while loading level: " + e.getMessage());
        }
        return new Wall[0][0];
    }


}
