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

    /**
     * Draw wall
     * @param g graphics to draw to
     * @param cellSize
     */
    @Override
    public void drawCell(Graphics g, int cellSize) {
        g.setColor(Color.BLUE);
        g.fillRect(getX() * cellSize, getY() * cellSize, cellSize, cellSize);
    }
}
