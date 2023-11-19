package gui.leveleditor;

import resourcehandler.ResourceHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 */
public class EntityChooserPanel extends JPanel {
    static JLabel currentEntityPanel = new JLabel();
    public EntityChooserPanel(){
        setLayout(new FlowLayout());
        createMenu();
        add(new JLabel("Selected entity:"));
        currentEntityPanel.setPreferredSize(new Dimension(22, 22));
        add(currentEntityPanel);
    }

    private void createMenu(){
        JMenuBar menuBar = new JMenuBar();
        for(String entityType : ResourceHandler.getEntityTypes()){
            // User shouldn't be able to add edibles to the map because the game will do it for them.
            // They also shouldn't be able to place frightened ghosts,
            // because it's an illegal state for them to start in.
            if(entityType.equals("frightened") || entityType.equals("edibles")) continue;
            JMenu entityMenu = new JMenu(entityType);
            for(String spriteName : ResourceHandler.getSpriteMap(entityType).keySet()){
                JMenuItem spriteItem = new JMenuItem(spriteName);
                BufferedImage spriteIcon = ResourceHandler.getSpriteMap(entityType).get(spriteName);
                spriteItem.setIcon(new ImageIcon(spriteIcon));
                spriteItem.addActionListener(e -> {
                    currentEntityPanel.setIcon(new ImageIcon(spriteIcon));
                    LevelEditorFrame.setCurrentEntityType(entityType);
                    LevelEditorFrame.setCurrentSprite(spriteName);
                });

                entityMenu.add(spriteItem);
            }
            menuBar.add(entityMenu);
        }
        add(menuBar);
    }



}
