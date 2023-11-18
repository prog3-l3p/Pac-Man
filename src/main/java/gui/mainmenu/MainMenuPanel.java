package gui.mainmenu;

import gui.Main;
import gui.game.GameFrame;
import gui.leveleditor.LevelEditorFrame;
import resourcehandler.ResourceHandler;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {
    private JButton levelEditorButton;
    private JButton gameButton;
    public MainMenuPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.YELLOW);
        createLabel();
        createButtonPanel();
        createPacPanel();
    }

    private void createLabel(){
        JLabel label = new JLabel("Pac-Man");
        label.setFont(ResourceHandler.getPacFont());
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(Color.BLACK);
        add(label, BorderLayout.NORTH);
    }

    private void createPacPanel(){
        JPanel pacPanel = new MenuPacAnimationPanel();
        add(pacPanel, BorderLayout.CENTER);
    }

    private void createButtonPanel(){
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.YELLOW);

        createGameButton();
        createLevelEditorButton();

        buttonPanel.add(levelEditorButton);
        buttonPanel.add(gameButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void createLevelEditorButton(){
        levelEditorButton = new JButton("Level Editor");
        levelEditorButton.setFont(ResourceHandler.getPacFont().deriveFont(12f));
        levelEditorButton.setForeground(Color.BLACK);
        levelEditorButton.setBackground(Color.YELLOW);
        levelEditorButton.setBorderPainted(false);
        levelEditorButton.addActionListener(e -> Main.setDisplayedFrame(new LevelEditorFrame()));
    }

    private void createGameButton(){
        gameButton = new JButton("Play Game");
        gameButton.setFont(ResourceHandler.getPacFont().deriveFont(12f));
        gameButton.setForeground(Color.BLACK);
        gameButton.setBackground(Color.YELLOW);
        gameButton.setBorderPainted(false);
        gameButton.addActionListener(e -> Main.setDisplayedFrame(new GameFrame()));
    }
}
