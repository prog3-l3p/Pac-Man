package gui.leveleditor;

import gamelogic.nonmoving.Wall;
import javax.swing.*;
import java.awt.*;
import java.io.*;


/**
 * This class is responsible for everything concerning the level editor.
 * It is a JPanel that contains a WallChooserPanel and an EditorViewPanel.
 * It also contains a save button and a load button.
 */
public class LevelEditorPanel extends JPanel {

    WallChooserPanel wallChooserPanel = new WallChooserPanel();
    EditorViewPanel viewPanel = new EditorViewPanel();
    private JButton saveButton = new JButton("Save");
    private JButton loadButton = new JButton("Load");
    public static String currentWallSprite = null;

    // Initialize the level editor panel
    public LevelEditorPanel(){
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        add(wallChooserPanel, BorderLayout.NORTH);
        add(viewPanel, BorderLayout.CENTER);

        wallChooserPanel.add(saveButton);
        wallChooserPanel.add(loadButton);

        saveButton.addActionListener(e -> showDialog("Save Level"));

        loadButton.addActionListener(e -> showDialog("Load Level"));
    }

    // Save the level to a file
    public void saveLevel(String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(viewPanel.getWalls());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Load the level from a file
    public void loadLevel(String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            viewPanel.setWalls((Wall[][]) inputStream.readObject());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Show a dialog to save the level
    private void showDialog(String title) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle(title);

        int userSelection = 0;

        switch(title) {
            case "Save Level" -> userSelection = fileChooser.showSaveDialog(this);
            case "Load Level" -> userSelection = fileChooser.showOpenDialog(this);
        }

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            switch (title) {
                case "Save Level" -> saveLevel(selectedFile.getAbsolutePath());
                case "Load Level" -> loadLevel(selectedFile.getAbsolutePath());
            }
        }
    }
}