package gui;

import gui.mainmenu.MainMenuFrame;
import resourcehandler.ResourceHandler;

import javax.swing.*;
import java.util.logging.Logger;

public class Main {
    public static final Logger logger = Logger.getLogger("Error");
    public static final ResourceHandler resourceHandler = new ResourceHandler();
    private static JFrame displayedFrame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        resourceHandler.init();
        displayedFrame = new MainMenuFrame();
        displayedFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        displayedFrame.setVisible(true);
    }

    public static void setDisplayedFrame(JFrame f){
        displayedFrame.dispose();
        displayedFrame = f;
        displayedFrame.setVisible(true);
    }
}