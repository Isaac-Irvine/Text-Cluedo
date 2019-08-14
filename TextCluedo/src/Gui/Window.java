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
        frame.setVisible(true);

        frame.add(this);
    }

    /**
     * Update the window
     */
    public void redraw() {
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
}
