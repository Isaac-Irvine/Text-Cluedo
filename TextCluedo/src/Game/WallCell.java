package Game;

import java.awt.*;

/**
 * Wall Cell
 */
public class WallCell extends Cell {

    /**
     * Wall Cell
     * Never free
     * @param x
     * @param y
     * @param chars characters that represent the wall
     */
    public WallCell(int x, int y, char[] chars) {
        super(x, y, chars, false);
    }

    @Override
    public void drawCell(Graphics2D g, int x, int y, int cellSize) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, cellSize, cellSize);
    }
}
