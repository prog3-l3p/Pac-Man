package gui.leveleditor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class LevelEditorMenu extends JMenuBar {

    File spriteDirectory = new File("res/sprites/walls");
    ArrayList<String> directories = new ArrayList<>();
    HashMap<String, BufferedImage> sprites = new HashMap<>();
    public LevelEditorMenu(){
        loadSprites(spriteDirectory);
        createWallMenu();
        createSaveButton();
        createLoadButton();
    }

    private void loadSprites(File dir) {
        try {
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    loadSprites(file);
                } else {
                    System.out.println("     file:" + file.getCanonicalPath());
                    sprites.put(file.getName(), ImageIO.read(file));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void createWallMenu(){
        // create a new menu for walls
        JMenu wallMenu = new JMenu("Walls");
        // create a menu item for each wall
        for(String entity : sprites.keySet()){
            JMenuItem item = new JMenuItem(entity.replaceFirst("\\.[A-Za-z0-9]+",""));
            item.addActionListener(e -> {
                LevelEditorPanel.viewPanel.setSelection(entity);
            });
            item.setIcon(new ImageIcon(sprites.get(entity)));
            wallMenu.add(item);
        }

        add(wallMenu);
    }

    private void createSaveButton(){
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showSaveDialog(this);
            File file = fileChooser.getSelectedFile();
            LevelEditorPanel.viewPanel.saveLevel(file.getAbsolutePath());
        });

        add(saveButton);
    }

    private void createLoadButton(){
        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(this);
            File file = fileChooser.getSelectedFile();
            LevelEditorPanel.viewPanel.loadLevel(file.getAbsolutePath());
        });

        add(loadButton);
    }
}
