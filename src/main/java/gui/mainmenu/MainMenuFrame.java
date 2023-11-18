package gui.mainmenu;

import resourcehandler.ResourceHandler;

import javax.swing.*;

/**
 * The main window of the application.
 */
public class MainMenuFrame extends JFrame {
    public MainMenuFrame() {
        super("Pac-Man");
        setIconImage(ResourceHandler.getSprites().get("GameWindowIcon.png"));
        add(new MainMenuPanel());
        pack();
    }

}
