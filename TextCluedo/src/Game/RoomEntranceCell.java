package Game;

import java.awt.*;

/**
 * Room Entrance Cell
 */
public class RoomEntranceCell extends Cell{
    private Cell.Direction direction;
    private Room room;

	/**
	 * Create an entrance to a room
	 * @param room
	 * @param x
	 * @param y
	 * @param chars
	 * @param direction
	 */
    public RoomEntranceCell(Room room, int x, int y, char[] chars, Cell.Direction direction) {
        super(x, y, chars, true);
        this.direction = direction;

        this.room = room;
    }

	/**
	 * Get the direction
	 * @return
	 */
	public Cell.Direction getDirection() {
    	return direction;
	}

    /**
     * Get the room
     */
    public Room getRoom() {
        return room;
    }

	/**
	 * Draw room entrance
	 * @param g graphics to draw to
	 * @param cellSize
	 */
	@Override
	public void drawCell(Graphics g, int cellSize) {
		g.setColor(Color.GREEN);
		g.fillRect(getX() * cellSize, getY() * cellSize, cellSize, cellSize);
	}
}
