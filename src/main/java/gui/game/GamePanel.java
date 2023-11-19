package gui.game;

import gamelogic.Entity;
import gamelogic.pacman.PacMan;
import resourcehandler.ResourceHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
/**
 * This class is responsible for the visual representation of the game.
 */
public class GamePanel extends JPanel {
    private PacMan pacMan;
    private final ArrayList<ArrayList<Entity>> level = ResourceHandler.getCurrentLevel();
    private static final int TIMER_DELAY = 150;
    private static final int SCREEN_WIDTH = 28*22;
    private static final int SCREEN_HEIGHT = 31*22;

    public GamePanel() {
        fixLevel();
        setBackground(Color.BLACK);

        Timer timer = new Timer(TIMER_DELAY, e -> {
            repaint();
            for(ArrayList<Entity> yAxis : level){
                for(Entity entity : yAxis){
                    entity.move();
                    if(entity.isPacMan() != null)
                        pacMan = entity.isPacMan();
                }
            }
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

    /**
     * @return the preferred size of the panel
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT);
    }

    /**
     * @param g  the <code>Graphics</code> context in which to paint
     */
    @Override
    public void paint(Graphics g){
        super.paint(g);
        for(ArrayList<Entity> yAxis : level){
            for(Entity e : yAxis){
                e.draw(g);
            }
        }
    }

    /**
     * This method fixes the level so that it is loaded in the correct orientation
     */
    private void fixLevel(){
        for (int i = 0; i < level.size(); i++) {
            for (int j = i + 1; j < level.get(i).size(); j++) {
                // Swap elements (i, j) and (j, i)
                Entity temp = level.get(i).get(j);
                level.get(i).set(j, level.get(j).get(i));
                level.get(j).set(i, temp);
            }
        }
    }

}
