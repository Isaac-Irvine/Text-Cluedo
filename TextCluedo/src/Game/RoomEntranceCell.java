package Game;

public class RoomEntranceCell extends Cell{
    private Cell.Direction direction;
    private Room room;

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
}
