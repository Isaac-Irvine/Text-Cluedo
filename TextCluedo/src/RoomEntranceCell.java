public class RoomEntranceCell extends Cell{
    private Cell.Direction direction;
    private String doorName;

    public RoomEntranceCell(int x, int y, char[] chars, Cell.Direction direction) {
        super(x, y, chars, true);
        this.direction = direction;
        doorName = direction.name() + " DOOR";
    }

	/**
	 * Get the direction
	 * @return
	 */
	public Cell.Direction getDirection() {
    	return direction;
	}
}
