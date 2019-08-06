package Game;

import java.util.*;

/**
 * Represents a suspect on the board.
 * Can be moved using directions
 */
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
		return getBoard().getAvailableNeighbours(getLocation(), visited);
	}

	/**
	 * Get a list of available room exits
	 * (Not blocked by players)
	 */
	public List<RoomEntranceCell> getAvaliableRoomExits() {
		if( currentRoom == null) { // not in room
			return new ArrayList<>();
		}


		List<RoomEntranceCell> cells = new ArrayList<>();

		for(RoomEntranceCell cell : currentRoom.getRoomEntrances()) {
			Cell exit = getBoard().getNeighbourCell(cell, cell.getDirection());
			if(exit.isFree()) cells.add(cell); // check the exit isn't blocked
		}

		return cells;
	}

	/**
	 * Move the suspect in a direction
	 */
	public void move(Cell.Direction direction) {
		Cell neighbour = getBoard().getNeighbourCell(getLocation(), direction);

		// check that the neighbour is a room.
		if(neighbour instanceof RoomEntranceCell) {
			RoomEntranceCell ent = (RoomEntranceCell) neighbour;
			if(direction.reverse() != ent.getDirection()) throw new IllegalStateException("Cannot move into a room entrance from the wrong direction.");

			enterRoom(ent.getRoom());
		}

		// regular movement
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
		currentRoom = null;
	}

	/**
	 * Move into a room
	 * @param room
	 */
    public void enterRoom(Room room) {
		currentRoom = room;
		Cell cell = currentRoom.getAvailableCell();
		moveTo(cell);
    }
}
