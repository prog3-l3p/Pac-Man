package gui.game;

import gamelogic.nonmoving.Food;
import gamelogic.nonmoving.Wall;
import gamelogic.pacman.PacMan;
import resourcehandler.ResourceHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * This class is responsible for everything concerning the game.
 */
public class GamePanel extends JPanel {
    private final Timer timer;
    private final PacMan pacMan = new PacMan(14,1);
    private Wall[][] level = ResourceHandler.getCurrentLevel();
    private Food[][] foods = ResourceHandler.createFoods();

    public GamePanel() {
        setBackground(Color.BLACK);

        timer = new Timer(150, e -> {
            repaint();
            pacMan.move();
        });
        timer.start();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                pacMan.keyPressed(e);
            }
        });

        setFocusable(true);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(28*22,31*22);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        for(Wall[] xAxis : level){
            for(Wall w : xAxis){
                w.draw(g);
            }
        }
        for(Food[] xAxis : foods)
            for(Food f : xAxis)
                if(f != null) {
                    f.draw(g);
                }
        pacMan.draw(g);

    }
}
