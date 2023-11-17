package gui.leveleditor;

import gui.ApplicationFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * A panel that allows the user to choose a wall sprite.
 */
public class WallChooserPanel extends JPanel {
    private HashMap<String, BufferedImage> sprites;
    JLabel currentWallPanel = new JLabel();
    JComboBox<String> wallChooser = new JComboBox<>();
    public WallChooserPanel(){
        setLayout(new FlowLayout());
        getInterestingSprites();
        createComboBox();
        add(new JLabel("Current wall:"));
        add(currentWallPanel);
    }

    // Gets all the wall sprites from the sprites HashMap in ApplicationFrame.
    private void getInterestingSprites() {
        sprites = new HashMap<>();
        for(String spritePath : ApplicationFrame.sprites.keySet()){
            if(spritePath.contains("walls")){
                String spriteShortHand = spritePath
                        .substring(spritePath.indexOf("walls") + 6, spritePath.indexOf(".png"));
                sprites.put(spriteShortHand, ApplicationFrame.sprites.get(spritePath));
            }
        }
    }

    // Creates the JComboBox that allows the user to choose a wall sprite.
    private void createComboBox(){
        for(String spriteName : sprites.keySet()){
            wallChooser.addItem(spriteName);
        }
        wallChooser.addActionListener(e -> {
            currentWallPanel.setIcon(new ImageIcon(sprites.get(wallChooser.getSelectedItem())));
            LevelEditorPanel.currentWallSprite = (String) wallChooser.getSelectedItem();
        });
        add(wallChooser);
    }
}
