package Gui;

import Game.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * The entire game view
 */
public class GameView extends Canvas {
    public static final int CELL_SIZE = 16;
    private static final int GAME_WIDTH = 26;
    private static final int GAME_HEIGHT = 27;
    private static final int CANVAS_WIDTH = GAME_WIDTH * CELL_SIZE;
    private static final int CANVAS_HEIGHT = GAME_HEIGHT * CELL_SIZE;

    private Game game;
    private Window window;
    private Player currentPlayer;
    private GameController controller;

    private JPanel buttonPanel, cardPanel;
    private Dice dice;

    /**
     * Create a game view for a player
     */
    public GameView(Window window, Game game, Player player) {
        this.game = game;
        game.setGameView(this);
        this.window = window;
        this.currentPlayer = player;
        this.controller = new GameController(this);

        window.removeAll();
        window.setLayout(new BoxLayout(window, BoxLayout.Y_AXIS));

        // menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        menuBar.add(gameMenu);
        JMenuItem restart = new JMenuItem("Restart");
        JMenuItem btMenu = new JMenuItem("Back To Menu");
        JMenuItem exit = new JMenuItem("Exit");
        gameMenu.add(restart);
        gameMenu.add(btMenu);
        gameMenu.add(exit);
        window.setMenuBar(menuBar);


        // create game canvas
        setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        setMaximumSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        addMouseListener(controller);
        window.add(this);

        // key listener
        setFocusable(true);
        requestFocus();
        addKeyListener(controller);

        // footer panel sizes
        int footerSize = (window.getHeight() - CANVAS_HEIGHT) / 2;
        dice = new Dice(footerSize);

        // button panel
        buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setSize(new Dimension(window.getWidth(), footerSize));
        window.add(buttonPanel);

        // card panel
        cardPanel = new JPanel(new FlowLayout());
        cardPanel.setSize(new Dimension(window.getWidth(), footerSize));
        window.add(cardPanel);

        swapPlayer(player);
        updatePlayerState();
    }

    /**
     * Update the buttons on the screen to reflect the player's current state
     */
    public void updatePlayerState() {
        buttonPanel.removeAll();

        Player.PlayerState state = currentPlayer.getCurrentState();

        // moving state
        if (state == Player.PlayerState.MOVING) {
            buttonPanel.add(dice);
            JLabel turnsLeftLabel = new JLabel("Moves Left: " + currentPlayer.getMovesLeft());
            buttonPanel.add(turnsLeftLabel);
        } else {
            // roll dice
            if (state == Player.PlayerState.WAITING) {
                JButton rollDice = new JButton("Roll Dice");
                rollDice.setFocusable(false);
                rollDice.addActionListener((ActionEvent e) -> {
                    currentPlayer.rollDice(dice);
                    repaint();
                });
                buttonPanel.add(rollDice);
            }
            // finish turn
            else {
                JButton finishTurn = new JButton("Finish Turn");
                finishTurn.setFocusable(false);
                finishTurn.addActionListener((ActionEvent e) -> {
                    currentPlayer.finishTurn();
                    repaint();
                });
                buttonPanel.add(finishTurn);
            }

            // accuse
            JButton accuse = new JButton("Accuse");
            accuse.setFocusable(false);
            buttonPanel.add(accuse);

            // suggest
            if (currentPlayer.getSuspect().getCurrentRoom() != null) {
				JButton suggest = new JButton("Suggest");
				suggest.setFocusable(false);
				buttonPanel.add(suggest);
            }

        }

        buttonPanel.repaint();
        window.redraw();
    }

    /**
     * Swap the current player in the window to the view of another player
     *
     * @param newPlayer
     */
    public void swapPlayer(Player newPlayer) {
        this.currentPlayer = newPlayer;

        window.setTitle(newPlayer.toString());

        // update the cards
        cardPanel.removeAll();
        for (Card card : newPlayer.getCards()) {
            JLabel c = new JLabel(card.toString());
            cardPanel.add(c);
        }

        window.redraw();
    }

    /**
     * Draw the current state of the board
     * <p>
     * TODO: Improve the drawing to include images.
     */
    public void paint(Graphics g) {
        Board board = game.getBoard();

        for (int y = 0; y < GAME_HEIGHT; y++) {
            for (int x = 0; x < GAME_WIDTH; x++) {
                Cell cell = board.getCell(x, y);
                cell.draw((Graphics2D) g, x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE);
            }
        }
    }

    /**
     * Get the current player
     *
     * @return
     */
    public Player getPlayer() {
        return currentPlayer;
    }

    /**
     * Get the game
     *
     * @return
     */
    public Game getGame() {
        return game;
    }
}
