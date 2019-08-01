public class RoomEntityCell extends Cell {
    /**
     * Cells in the room where entities can appear
     * @param x
     * @param y
     * @param chars
     */
    public RoomEntityCell(int x, int y, char[] chars) {
        super(x, y, chars, true);
    }
}
