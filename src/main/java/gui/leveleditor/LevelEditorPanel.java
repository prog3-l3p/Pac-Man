package gui.leveleditor;

import javax.swing.*;
import java.awt.*;

public class LevelEditorPanel extends JPanel {

    static LevelEditorMenu menu = new LevelEditorMenu();
    static EditorViewPanel viewPanel = new EditorViewPanel();

    public LevelEditorPanel(){
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        add(menu, BorderLayout.NORTH);
        add(viewPanel, BorderLayout.CENTER);
    }



}