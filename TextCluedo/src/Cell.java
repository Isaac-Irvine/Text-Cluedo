public abstract class Cell {
    private int x;
    private int y;

    private Item item = null;

    public abstract boolean isFree();
    public abstract char[] getChars();
}
