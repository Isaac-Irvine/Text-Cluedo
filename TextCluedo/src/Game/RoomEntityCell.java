package Game;

import java.awt.*;

/**
 * Cells in the room where entities can appear
 */
public class RoomEntityCell extends Cell {
    private Room room;

    /**
     * Cells in the room where entities can appear
     *
     * @param x
     * @param y
     * @param chars
     */
    public RoomEntityCell(Room room, int x, int y, char[] chars) {
        super(x, y, chars, true);

        this.room = room;
    }


    /**
     * Get the room
     * @return
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Draw room entity cell
     * @param g graphics to draw to
     * @param cellSize
     */
    @Override
    public void drawCell(Graphics g, int cellSize) {
        g.setColor(Color.WHITE);
        g.fillRect(getX() * cellSize, getY() * cellSize, cellSize, cellSize);
    }
}
