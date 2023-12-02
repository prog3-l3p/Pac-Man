package gui.mainmenu;

import utility.ResourceHandler;

import javax.swing.*;

/**
 * The main window of the application.
 */
public class MainMenuFrame extends JFrame {
    /**
     * Constructor
     */
    public MainMenuFrame() {
        super("Pac-Man");
        setIconImage(ResourceHandler.getIcon("GameWindowIcon"));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(new MainMenuPanel());
        pack();
    }

}
