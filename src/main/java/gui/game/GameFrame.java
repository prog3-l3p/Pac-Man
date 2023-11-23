package gui.game;

import utility.ResourceHandler;

import javax.swing.*;
import java.awt.*;

/**
 * GameFrame class
 */
public class GameFrame extends JFrame {
    public GameFrame(){
        super("Pac-Man");
        setLayout(new BorderLayout());
        setIconImage(ResourceHandler.getIcon("GameWindowIcon"));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        add(new GamePanel(), BorderLayout.CENTER);
        pack();
    }
}
