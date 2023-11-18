package gui.game;

import resourcehandler.ResourceHandler;

import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame(){
        super("Pac-Man");
        setIconImage(ResourceHandler.getSprites().get("GameWindowIcon.png"));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(new GamePanel());
        pack();
    }
}
