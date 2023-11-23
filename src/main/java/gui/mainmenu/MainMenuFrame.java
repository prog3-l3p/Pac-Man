package gui.mainmenu;

import utility.ResourceHandler;

import javax.swing.*;

/**
 * The main window of the application.
 */
public class MainMenuFrame extends JFrame {
    public MainMenuFrame() {
        super("Pac-Man");
        setIconImage(ResourceHandler.getIcon("GameWindowIcon"));
        setResizable(false);
        setLocationRelativeTo(null);
        add(new MainMenuPanel());
        pack();
    }

}
