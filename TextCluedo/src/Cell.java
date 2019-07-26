public abstract class Cell {
    private final int x;
    private final int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private Item item = null;

    public abstract boolean isFree();
    public abstract char[] getChars();
}
