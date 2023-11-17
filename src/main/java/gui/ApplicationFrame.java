package gui;

import gui.leveleditor.*;
import javax.swing.*;
import java.awt.*;

public class ApplicationFrame extends JFrame {

    public ApplicationFrame() {
        super("Pac-Man");
        setIconImage(new ImageIcon("res/sprites/pm_big/right_2.png").getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(new GamePanel(), BorderLayout.CENTER);
        add(new LevelEditorPanel(), BorderLayout.EAST);
        pack();
    }
}
