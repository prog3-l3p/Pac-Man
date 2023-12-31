package gui.mainmenu;


import gamelogic.entities.Entity;
import gamelogic.entities.nonmoving.Food;
import gamelogic.entities.moving.PacMan;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static utility.GameConstants.CELL_SIZE;
import static utility.GameConstants.TIMER_DELAY;

/**
 * This class is responsible for the Pac-Man animation in the main menu.
 */
public class MenuPacAnimationPanel extends JPanel {
    /**
     * Stores the PacMan entity
     */
    private final PacMan pacMan = new PacMan(0, 0);
    /**
     * Stores the food entities
     */
    ArrayList<Entity> foods = new ArrayList<>();

    /**
     * Constructor
     */
    public MenuPacAnimationPanel(){
        setBackground(Color.BLACK);
        initFoods();
        pacMan.setInitialDirection("right");
        Timer timer = new Timer(TIMER_DELAY, e -> {
            repaint();
            pacMan.menuMove(getWidth() / CELL_SIZE);
        });
        timer.start();
    }

    /**
     * Initialize the food entities
     */
    private void initFoods(){
        for(int i = 0; i < 23; i ++){
            Food f = new Food(i, 0);
            foods.add(f);
        }
    }

    /**
     * @return the preferred size of the panel
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100,22);
    }

    /**
     * Paints Pac-Man and the food entities
     * @param g the <code>Graphics</code> context in which to paint
     */
    @Override
    public void paint(Graphics g){
        super.paint(g);
        for(Entity e : foods){
            if(e.getX() == pacMan.getX()){
                e.eatenBy(pacMan);
            }
            if(e.getX() > pacMan.getX()){
                e.setSprite("food");
            }
            e.draw(g);
        }
        pacMan.draw(g);
    }
}
