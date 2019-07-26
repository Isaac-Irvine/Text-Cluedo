public class RoomCell extends Cell {
    private char[] chars;

    /**
     * The room as
     * @param x
     * @param y
     */
    public RoomCell(int x, int y) {
        super(x, y);

        this.chars = null;
    }

    /**
     * The room as a
     * @param x
     * @param y
     * @param chars
     */
    public RoomCell(int x, int y, char[] chars) {
        super(x, y);
        if(chars.length != 2) throw new IllegalArgumentException("Cells may only be represented by 2 characters");

        this.chars = chars;
    }
    @Override
    public boolean isFree() {
        return this.chars == null;
    }

    @Override
    public char[] getChars() {
        if(chars == null)
            return new char[]{' ', ' '};
        else return chars;
    }
}
