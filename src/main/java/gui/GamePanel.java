package gui;

import gamelogic.pacman.PacMan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class GamePanel extends JPanel {
    private final Timer timer;
    private final PacMan pacMan = new PacMan();

    public GamePanel() {
        setBackground(Color.BLACK);

        timer = new Timer(100, e -> {
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
        return new Dimension(29*22,32*22);
    }

    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(pacMan.getCurrentSprite(), pacMan.getX(), pacMan.getY(), this);
    }






}
