package Gui;

import Game.*;

import javax.swing.*;
import java.awt.*;

/**
 * The entire game view
 */
public class GameView implements View {
    private static final int CELL_SIZE = 12;
    private static final int CANVAS_WIDTH = 26 * CELL_SIZE;
    private static final int CANVAS_HEIGHT = 27 * CELL_SIZE;

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
        gameCanvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        gameCanvas.setMaximumSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        gameCanvas.setBackground(Color.BLUE);
        window.add(gameCanvas);

        // footer panel sizes
        int footerSize = (window.getHeight() - CANVAS_HEIGHT)/2;

        // button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(new JButton("Roll Dice"));
        buttonPanel.add(new JButton("Accuse"));
        buttonPanel.setSize(new Dimension(window.getWidth(), footerSize));
        window.add(buttonPanel);

        // card panel
        JPanel cardPanel = new JPanel(new FlowLayout());
        cardPanel.add(new JButton("Card1"));
        cardPanel.add(new JButton("Card2"));
        cardPanel.setSize(new Dimension(window.getWidth(), footerSize));
        window.add(cardPanel);


        window.redraw();
    }
}
