package gui.mainmenu;

import gamelogic.pacman.PacMan;

import javax.swing.*;
import java.awt.*;

public class MenuPacAnimationPanel extends JPanel {
    private final Timer timer;
    private final PacMan pacMan = new PacMan(0, this.getHeight()/2);

    public MenuPacAnimationPanel(){
        setBackground(Color.BLACK);
        timer = new Timer(100, e -> {
            repaint();
            pacMan.menuMove(this.getWidth());
        });
        timer.start();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100,22);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(pacMan.getCurrentSprite(), pacMan.getX(), pacMan.getY(), this);
    }
}
