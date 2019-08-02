import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

		currentRoom = null;
	}

	/**
	 * The room you are currently in.
	 * Could be null.
	 * @return
	 */
	public Room getCurrentRoom() {
		return currentRoom;
	}

	/**
	 * Get a set of available directions coming from the suspect.
	 */
	public Set<Cell.Direction> getAvaliableDirections(Set<Cell> visited) {
		if( currentRoom != null) { // no avaliable directions if you are in a room
			return new HashSet<>();
		}
		return getBoard().getAvaliableNeighbours(getLocation(), visited);
	}

	/**
	 * Get a list of all the room exits
	 */
	public List<RoomEntranceCell> getExits() {
		if(currentRoom == null) {
			return new ArrayList<>(); // no exits because not in a room
		}

		return currentRoom.getRoomEntrances();
	}

	/**
	 * Move the suspect in a direction
	 */
	public void move(Cell.Direction direction) {
		Cell neighbour = getBoard().getNeighbourCell(getLocation(), direction);

		// check that the neighbour is a room.
		if(neighbour instanceof RoomEntranceCell) {
			// TODO enter room - but check that the direction is correct
		}

		else {
			moveTo(neighbour);
		}
	}

	/**
	 * Exit a room
	 */
	public void exitRoom(RoomEntranceCell exit) {
		Cell neighbour = getBoard().getNeighbourCell(exit, exit.getDirection());
		moveTo(neighbour);
	}
}
