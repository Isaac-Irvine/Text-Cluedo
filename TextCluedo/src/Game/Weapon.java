package Game;

import java.awt.*;

/**
 * Weapon on the board
 */
public class Weapon extends Entity {
	/**
	 * Create new weapon object
	 * @param board
	 * @param room
	 * @param chars
	 */
	public Weapon(Board board, Room room, char[] chars){
		super(board, room.getAvailableCell(), chars);
	}

	/**
	 * Draw the weapon
	 * @param g the graphics to draw to
	 * @param x
	 * @param y
	 * @param cellSize
	 */
	@Override
	public void draw(Graphics2D g, int x, int y, int cellSize) {
		g.setColor(Color.GRAY);
		g.fillRect(x + cellSize/4, y+cellSize/4, cellSize/2, cellSize/2);
	}
}
