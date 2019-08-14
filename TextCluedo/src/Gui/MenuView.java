package Gui;

import Game.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.awt.*;
import java.util.List;

/**
 * Represents the
 */
public class MenuView implements View {
    private Window window;
    private JComboBox playerSelect;
    private JPanel characterSelector;
    private int nPlayers;
    private List<JTextField> playerNames = new ArrayList<>();
    private List<JComboBox> playerCharacters = new ArrayList<>();

    /**
     * Create a menu view
     */
    public MenuView() {
        window = new Window("Pick players", 640, 480);
        window.setLayout(new BoxLayout(window, BoxLayout.Y_AXIS));

        // player number selector
        nPlayers = 3;

        JPanel playerSelectPanel = new JPanel(new FlowLayout());

        JLabel playerSelectLabel = new JLabel("Select amount of Players:");

        playerSelect = new JComboBox(new String[]{"3", "4", "5", "6"});
        playerSelect.setSelectedIndex(0);
        playerSelectPanel.add(playerSelectLabel);
        playerSelectPanel.add(playerSelect);
        window.add(playerSelectPanel);

        // create player amount selector updater
        playerSelect.addActionListener((ActionEvent e) ->
        {
            nPlayers = playerSelect.getSelectedIndex() + 3;
            recreateCharacterSelect();
        });

        // character selector
        characterSelector = new JPanel();
        window.add(characterSelector);
        recreateCharacterSelect();

        // bottom panel
        JPanel footerPanel = new JPanel(new FlowLayout());
        window.add(footerPanel);
        JButton doneButton = new JButton("Done");
        JButton exitButton = new JButton("Exit");
        footerPanel.add(doneButton);
        footerPanel.add(exitButton);

        // redraw
        window.redraw();

    }

    /**
     * Create the character selector
     */
    private void recreateCharacterSelect() {
        // remove previous items
        characterSelector.removeAll();
        playerNames.clear();
        playerCharacters.clear();

        // reformat layout
        characterSelector.setLayout(new GridLayout(nPlayers, 2));

        // create fields
        for (int i = 1; i <= nPlayers; i++) {
            JTextField field = new JTextField("Player " + i);
            JComboBox combo = new JComboBox(Game.allSuspects);
            characterSelector.add(field);
            characterSelector.add(combo);

            playerNames.add(field);
            playerCharacters.add(combo);
        }

        // redraw
        window.redraw();
    }
}
