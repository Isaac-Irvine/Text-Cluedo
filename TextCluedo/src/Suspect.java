import java.util.Set;

public class Suspect extends Entity {
	private Room currentRoom;


	/**
	 * Create a new suspect object.
	 * This adds it to the board too.
	 * @param board
	 * @param x
	 * @param y
	 * @param chars
	 */
	public Suspect(Board board, int x, int y, char[] chars) {
		super(board, board.getCell(x, y), chars);
	}

	/**
	 * Get a set of avaliable directions coming from the suspect.
	 */
	public Set<Cell.Direction> getAvaliableDirections() {
		return getBoard().getAvaliableNeighbours(getLocation());
	}
}
