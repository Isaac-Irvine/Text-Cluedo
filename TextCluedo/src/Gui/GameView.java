package Gui;

import Game.*;

import javax.swing.*;
import java.awt.*;

/**
 * The entire game view
 */
public class GameView implements View {
    private Game game;
    private Window window;
    private Canvas gameCanvas;
    private Player currentPlayer;

    /**
     * Create the game view
     * @param window
     */
    public GameView(Window window, Game game, Player player) {
        this.game = game;
        this.window = window;
        this.currentPlayer = player;

        window.removeAll();
        window.setLayout(new BoxLayout(window, BoxLayout.Y_AXIS));

        // create game canvas
        gameCanvas = new Canvas();
        window.add(gameCanvas);

        // button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(new JButton("Roll Dice"));
        buttonPanel.add(new JButton("Accuse"));
        window.add(buttonPanel);

        // card panel
        JPanel cardPanel = new JPanel(new FlowLayout());
        cardPanel.add(new JButton("Card1"));
        cardPanel.add(new JButton("Card2"));
        window.add(cardPanel);


        window.redraw();
    }
}
