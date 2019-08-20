package Game;

import java.awt.*;

/**
 * Free Cell
 */
public class FreeCell extends Cell {
    /**
     * Free cell. By default will always be blank and you can move into it.
     * @param x
     * @param y
     */
    public FreeCell(int x, int y) {
        super(x, y, "  ".toCharArray(), true);
    }

    @Override
    public void drawCell(Graphics g, int cellSize) {
        g.setColor(Color.WHITE);
        g.fillRect(getX() * cellSize, getY() * cellSize, cellSize, cellSize);
    }
}
