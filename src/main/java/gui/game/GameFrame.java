package gui.game;

import utility.ResourceHandler;

import javax.swing.*;

/**
 * GameFrame class
 */
public class GameFrame extends JFrame {
    public GameFrame(){
        super("Pac-Man");
        setIconImage(ResourceHandler.getIcon("GameWindowIcon"));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(new GamePanel());
        pack();
    }
}
