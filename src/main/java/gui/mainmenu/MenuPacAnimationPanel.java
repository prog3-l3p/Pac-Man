package gui.mainmenu;

import entities.Entity;
import entities.nonmoving.Food;
import entities.moving.PacMan;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class is responsible for the Pac-Man animation in the main menu.
 */
public class MenuPacAnimationPanel extends JPanel {
    private final PacMan pacMan = new PacMan(0, 0);
    ArrayList<Entity> foods = new ArrayList<>();

    public MenuPacAnimationPanel(){
        setBackground(Color.BLACK);
        initFoods();
        pacMan.setInitialDirection("right");
        Timer timer = new Timer(150, e -> {
            repaint();
            pacMan.menuMove(getWidth() / 22);
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
