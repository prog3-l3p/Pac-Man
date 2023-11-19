package gui.game;

import gamelogic.Entity;
import gamelogic.nonmoving.Food;
import gamelogic.nonmoving.Wall;
import gamelogic.pacman.PacMan;
import resourcehandler.ResourceHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * This class is responsible for everything concerning the game.
 */
public class GamePanel extends JPanel {
    private final Timer timer;
    private final PacMan pacMan = new PacMan(14,1);
    private ArrayList<ArrayList<Entity>> level = ResourceHandler.getCurrentLevel();
    {
        for (int i = 0; i < level.size(); i++) {
            for (int j = i + 1; j < level.get(i).size(); j++) {
                // Swap elements (i, j) and (j, i)
                Entity temp = level.get(i).get(j);
                level.get(i).set(j, level.get(j).get(i));
                level.get(j).set(i, temp);
            }
        }
    }
   /* private Food[][] foods = ResourceHandler.createFoods();*/

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
        for(ArrayList<Entity> yAxis : level){
            for(Entity e : yAxis){
                e.draw(g);
            }
        }

        pacMan.draw(g);

    }
}
