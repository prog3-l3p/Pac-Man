package gui.game;

import entities.Entity;
import entities.moving.PacMan;
import utility.ResourceHandler;

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
        findPacMan();
        addObservers();
        setBackground(Color.BLACK);
        Timer timer = new Timer(TIMER_DELAY, e -> {
            repaint();
            for(ArrayList<Entity> yAxis : level){
                for(Entity entity : yAxis){
                    entity.move();
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

    private void findPacMan(){
        for(ArrayList<Entity> yAxis : level){
            for(Entity entity : yAxis){
                if(entity.isPacMan() != null)
                    pacMan = entity.isPacMan();
            }
        }
    }

    private void addObservers(){
        for(ArrayList<Entity> yAxis : level){
            for(Entity entity : yAxis){
                if(entity.isGhost() != null)
                    entity.isGhost().pacManObserverAdd(pacMan);
            }
        }
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



}
