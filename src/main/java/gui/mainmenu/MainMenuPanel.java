package gui.mainmenu;

import gui.Main;
import gui.game.GameFrame;
import gui.leveleditor.LevelEditorFrame;
import utility.ResourceHandler;

import javax.swing.*;
import java.awt.*;

/**
 * The visual representation of the main menu.
 */
public class MainMenuPanel extends JPanel {
    /**
     * Stores the button that allows the user to enter the level editor
     */
    private JButton levelEditorButton;
    /**
     * Stores the button that allows the user to play the game
     */
    private JButton gameButton;
    /**
     * Stores the font size
     */
    private static final float FONT_SIZE = 12F;

    /**
     * Constructor
     */
    public MainMenuPanel() {
        setLayout(new BorderLayout());

        setBackground(Color.YELLOW);
        createLabel();
        createButtonPanel();
        createPacPanel();
    }

    /**
     * Create the label that displays the title of the game
     */
    private void createLabel(){
        JLabel label = new JLabel("Pac-Man");
        label.setFont(ResourceHandler.getPacFont());
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(Color.BLACK);
        add(label, BorderLayout.NORTH);
    }

    /**
     * Creates the panel responsible for the menu animation
     */
    private void createPacPanel(){
        MenuPacAnimationPanel pacPanel = new MenuPacAnimationPanel();
        add(pacPanel, BorderLayout.CENTER);
    }

    /**
     * Creates the panel responsible for the buttons
     */
    private void createButtonPanel(){
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.YELLOW);

        createGameButton();
        createLevelEditorButton();

        buttonPanel.add(levelEditorButton);
        buttonPanel.add(gameButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Creates the button that allows the user to enter the level editor
     */
    private void createLevelEditorButton(){
        levelEditorButton = new JButton("Level Editor");
        levelEditorButton.setFont(ResourceHandler.getPacFont().deriveFont(FONT_SIZE));
        levelEditorButton.setForeground(Color.BLACK);
        levelEditorButton.setBackground(Color.YELLOW);
        levelEditorButton.setBorderPainted(false);
        levelEditorButton.addActionListener(e -> Main.setDisplayedFrame(new LevelEditorFrame()));
    }

    /**
     * Creates the button that allows the user to play the game
     */
    private void createGameButton(){
        gameButton = new JButton("Play Game");
        gameButton.setFont(ResourceHandler.getPacFont().deriveFont(FONT_SIZE));
        gameButton.setForeground(Color.BLACK);
        gameButton.setBackground(Color.YELLOW);
        gameButton.setBorderPainted(false);
        gameButton.addActionListener(e -> {
            ResourceHandler.levelSelectDialog(this);
            if(ResourceHandler.getCurrentLevel() != null)
                Main.setDisplayedFrame(new GameFrame());
            else Main.setDisplayedFrame(new MainMenuFrame());
        });
    }

}
