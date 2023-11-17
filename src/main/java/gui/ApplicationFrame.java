package gui;

import gui.leveleditor.LevelEditorPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

/**
 * The main window of the application.
 */
public class ApplicationFrame extends JFrame {

    // The sprites used in the game.
    public static final HashMap<String, BufferedImage> sprites = new HashMap<>();
    public ApplicationFrame() {
        super("Pac-Man");
        initSprites();
        setIconImage(sprites.get("pacman.png"));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(new GamePanel(), BorderLayout.CENTER);
        add(new GameMenu(), BorderLayout.NORTH);
        add(new LevelEditorPanel(), BorderLayout.EAST);
        pack();
    }
    // Loads all the sprites from the res/sprites directory.
    private void initSprites(){
       File workingDirectory = new File(System.getProperty("user.dir"));
       File spriteDirectory = new File(workingDirectory, "res/sprites");
       getAllSprites(spriteDirectory);
    }

    // Recursively gets all the sprites from the given directory.
    private void getAllSprites(File dir) {
        try {
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    getAllSprites(file);
                } else {
                    int neededIndex = file.getPath().indexOf("sprites") + 8;
                    String spriteName = file.getPath().substring(neededIndex);
                    sprites.put(spriteName, ImageIO.read(file));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
