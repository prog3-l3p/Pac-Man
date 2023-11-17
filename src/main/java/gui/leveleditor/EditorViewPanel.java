package gui.leveleditor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;

public class EditorViewPanel extends JPanel {

        private static String selectedSpritePath = "res/sprites/walls/empty.png";
        private static ArrayList<String> levelSpritePaths = new ArrayList<>();
        public EditorViewPanel() {
            setLayout(new GridLayout(31, 28));
            try {
                for (int i = 0; i < 31 * 28; i++) {
                    levelSpritePaths.add("res/sprites/walls/empty.png");
                    JLabel label = new JLabel();
                    label.setIcon(new ImageIcon(ImageIO.read(new File(selectedSpritePath))));
                    label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    add(label);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JLabel label = (JLabel) getComponentAt(e.getPoint());
                    int row = e.getPoint().y / 24;
                    int col = e.getPoint().x / 24;
                    levelSpritePaths.set(row * 28 + col, selectedSpritePath);
                    try{
                        label.setIcon(new ImageIcon(ImageIO.read(new File(selectedSpritePath))));
                    } catch (Exception exception){
                        exception.printStackTrace();
                    }
                }
            });
        }

        public void setSelection(String spriteName) {
            selectedSpritePath = "res/sprites/walls/" + spriteName;
            System.out.println(selectedSpritePath);
        }

        public void saveLevel(String fileName) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                oos.writeObject(levelSpritePaths);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public void loadLevel(String fileName) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
                levelSpritePaths = (ArrayList<String>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            for(int i = 0; i < 31*28; i++){
                try {
                    JLabel label;
                    label = (JLabel) getComponent(i);
                    label.setIcon(new ImageIcon(ImageIO.read(new File(levelSpritePaths.get(i)))));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


}
