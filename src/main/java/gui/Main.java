package gui;

import gui.mainmenu.MainMenuFrame;
import utility.ResourceHandler;

import javax.swing.*;
import java.util.logging.Logger;

/**
 * The entry point of the program
 */
public class Main {
    /**
     * The logger for the program
     */
    public static final Logger logger = Logger.getLogger("Error");
    /**
     * The frame currently being displayed
     */
    private static JFrame displayedFrame;

    /**
     * The entry point of the program
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    /**
     * Creates and shows the GUI
     */
    private static void createAndShowGUI() {
        ResourceHandler.init();
        displayedFrame = new MainMenuFrame();
        displayedFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        displayedFrame.setVisible(true);
    }

    /**
     * Sets the frame to be displayed
     * @param f The frame to be displayed
     */
    public static void setDisplayedFrame(JFrame f){
        displayedFrame.dispose();
        displayedFrame = f;
        displayedFrame.setVisible(true);
    }
}