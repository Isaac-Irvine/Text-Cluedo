public class FreeCell extends Cell {
    /**
     * Free cell. By default will always be blank and you can move into it.
     * @param x
     * @param y
     */
    public FreeCell(int x, int y) {
        super(x, y, new char[] {' ', ' '}, true);
    }
}
