package gui.game;

import gamelogic.EntityObserver;
import gamelogic.entities.Entity;
import gamelogic.entities.moving.PacMan;
import gamelogic.entities.moving.ghosts.*;
import utility.ResourceHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

import static utility.GameConstants.*;

/**
 * This class is responsible for the visual representation of the game.
 */
public class GamePanel extends JPanel {
    private PacMan pacMan;
    private final ArrayList<Ghost> ghosts = new ArrayList<>();
    private final ArrayList<ArrayList<Entity>> level = ResourceHandler.getCurrentLevel();
    private final HashMap<String, Point> locations = ResourceHandler.getInitialLocations();

    private EntityObserver observer = new EntityObserver();
    public GamePanel() {
        JLabel scoreLabel = new JLabel();
        setBackground(Color.BLACK);
        scoreLabel.setForeground(Color.WHITE);
        add(scoreLabel);
        initEntities();
        addObservers();
        Timer timer = new Timer(TIMER_DELAY, e -> {
            repaint();
            scoreLabel.setText("Score: " + observer.getScore());
            pacMan.move();
            for(Ghost ghost : ghosts){
                ghost.move();
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

    private void initEntities(){
        // Initialize PacMan and the ghosts
        // could be better...
        pacMan = new PacMan(locations.get("pacman").x, locations.get("pacman").y);
        level.get(pacMan.getY()).set(pacMan.getX(), pacMan);

        Blinky blinky = new Blinky(locations.get("blinky").x, locations.get("blinky").y);
        level.get(blinky.getY()).set(blinky.getX(), blinky);

        Inky inky = new Inky(locations.get("inky").x, locations.get("inky").y);
        inky.setObservedBlinky(blinky);
        level.get(inky.getY()).set(inky.getX(), inky);

        Pinky pinky = new Pinky(locations.get("pinky").x, locations.get("pinky").y);
        level.get(pinky.getY()).set(pinky.getX(), pinky);

        Clyde clyde = new Clyde(locations.get("clyde").x, locations.get("clyde").y);
        level.get(clyde.getY()).set(clyde.getX(), clyde);

        ghosts.add(blinky);
        ghosts.add(inky);
        ghosts.add(pinky);
        ghosts.add(clyde);

    }

    private void addObservers(){
        for(Ghost ghost : ghosts){
            pacMan.addObserver(ghost);
        }
        for(ArrayList<Entity> yAxis : level){
            for(Entity e : yAxis){
                e.addObserver(observer);
            }
        }
    }

    /**
     * @return the preferred size of the panel
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COLUMN_COUNT * CELL_SIZE,ROW_COUNT * CELL_SIZE);
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
