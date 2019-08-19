package Gui;

import javax.swing.*;
import java.awt.*;

/**
 * Represents a window.
 * Made from a JFrame with a JPanel containing the whole
 */
public class Window extends JPanel {
    private JFrame frame;

    /**
     * Create a window
     * @param name Title of the JFrame
     * @param width width of the window
     * @param height height of the window
     */
    public Window(String name, int width, int height) {
        frame = new JFrame(name);
        frame.setSize(new Dimension(width, height));
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        frame.add(this);
    }

    /**
     * Update the window
     */
    public void redraw() {
        repaint();
        frame.revalidate();
    }


    /**
     * Width of the window
     * @return
     */
    public int getWidth() {
        return frame.getWidth();
    }

    /**
     * Height of the window
     * @return
     */
    public int getHeight() {
        return frame.getHeight();
    }

    /**
     * Close the window
     */
    public void close() {
        frame.dispose();
    }

    /**
     * Change the title of the window
     * @param title
     */
    public void setTitle(String title) {
        frame.setTitle(title);
    }

    /**
     * Set the menu bar
     * @param menuBar
     */
    public void setMenuBar(JMenuBar menuBar) {
        frame.setJMenuBar(menuBar);
    }
}
