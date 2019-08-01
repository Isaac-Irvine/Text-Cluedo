public class RoomEntranceCell extends Cell{
    private Cell.Direction direction;


    public RoomEntranceCell(int x, int y, char[] chars, Cell.Direction direction) {
        super(x, y, chars, true);
        this.direction = direction;
    }
}
