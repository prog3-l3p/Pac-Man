package gui.leveleditor;

import utility.ResourceHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import static utility.GameConstants.CELL_SIZE;
/**
 *
 */
public class EntityChooserPanel extends JPanel {
    static JLabel currentEntityPanel = new JLabel();
    public EntityChooserPanel(){
        setLayout(new FlowLayout());
        createMenu();
        add(new JLabel("Selected entity:"));
        currentEntityPanel.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
        add(currentEntityPanel);
    }

    /**
     * Create the menu that allows the user to select an entity to place on the map
     * The menu is created based on the entities that are available in the resource handler
     * The user can select an entity by clicking on it in the menu
     * When an entity is selected, the current entity panel is updated to show the selected entity
     * The current entity type and sprite are also updated
     * The user can then place the selected entity on the map
     */
    private void createMenu(){
        JMenuBar menuBar = new JMenuBar();
        for(String entityType : ResourceHandler.getEntityTypes()){
            // User shouldn't be able to add edibles to the map because the game will do it for them.
            // They also shouldn't be able to place frightened or dead ghosts,
            // because it's an illegal state for them to start in.
            if(entityType.equals("frightened") || entityType.equals("dead")) continue;
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
