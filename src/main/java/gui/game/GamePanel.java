package gui.game;

<<<<<<< Updated upstream
import gamelogic.nonmoving.Wall;
import gamelogic.pacman.PacMan;
=======
import gamelogic.EntityObserver;
import gamelogic.entities.Entity;
import gamelogic.entities.moving.PacMan;
import gamelogic.entities.moving.ghosts.*;
import utility.ResourceHandler;
>>>>>>> Stashed changes

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
<<<<<<< Updated upstream
=======
import java.util.ArrayList;
import java.util.HashMap;
>>>>>>> Stashed changes

/**
 * This class is responsible for everything concerning the game.
 */
public class GamePanel extends JPanel {
<<<<<<< Updated upstream
    private final Timer timer;
    private final PacMan pacMan = new PacMan(31*11,28*11);
    private Wall[][] level;

    public GamePanel() {

=======
    private PacMan pacMan;
    private final ArrayList<Ghost> ghosts = new ArrayList<>();
    private final ArrayList<ArrayList<Entity>> level = ResourceHandler.getCurrentLevel();
    private final HashMap<String, Point> locations = ResourceHandler.getInitialLocations();

    private EntityObserver observer = new EntityObserver();
    private static final int TIMER_DELAY = 130;
    private static final int SCREEN_WIDTH = 28*22;
    private static final int SCREEN_HEIGHT = 31*22;

    public GamePanel() {
        JLabel scoreLabel = new JLabel();
        scoreLabel.setForeground(Color.WHITE);
        add(scoreLabel);
        initEntities();
        addObservers();
>>>>>>> Stashed changes
        setBackground(Color.BLACK);

        timer = new Timer(100, e -> {
            repaint();
            pacMan.move();
<<<<<<< Updated upstream
=======
            for(Ghost ghost : ghosts){
                ghost.move();
            }
            scoreLabel.setText("Score: " + observer.getScore());

>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
=======
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
>>>>>>> Stashed changes
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(29*22,32*22);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(pacMan.getCurrentSprite(), pacMan.getX(), pacMan.getY(), this);
    }
}
