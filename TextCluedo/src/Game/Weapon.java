package Game;

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
}
