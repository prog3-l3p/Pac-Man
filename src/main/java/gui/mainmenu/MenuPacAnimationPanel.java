package gui.mainmenu;

import gamelogic.Entity;
import gamelogic.nonmoving.Food;
import gamelogic.pacman.PacMan;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class MenuPacAnimationPanel extends JPanel {
    private final Timer timer;
    private final PacMan pacMan = new PacMan(0, 0);
    ArrayList<Entity> foods = new ArrayList<>();

    Random random = new Random();

    public MenuPacAnimationPanel(){
        setBackground(Color.BLACK);
        initFoods();
        timer = new Timer(200, e -> {
            repaint();
            pacMan.menuMove(this.getWidth() / pacMan.getWidth());
        });
        timer.start();
    }

    private void initFoods(){
        for(int i = 0; i < 23; i ++){
            Food f = new Food(i, 0);
            foods.add(f);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100,22);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        for(Entity e : foods){
            if(e.getX() < pacMan.getX()){
                e.setSprite("none");
            }
            if(e.getX() > pacMan.getX()){
                e.setSprite("food");
            }
            e.draw(g);
        }
        pacMan.draw(g);
    }
}
