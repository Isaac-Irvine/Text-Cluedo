public class WallCell extends Cell {

    /**
     * Wall Cell
     * Never free
     * @param x
     * @param y
     * @param chars characters that represent the wall
     */
    public WallCell(int x, int y, char[] chars) {
        super(x, y, chars, false);
    }
}
