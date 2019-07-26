public class WallCell extends Cell {
    private char[] chars;

    public WallCell(int x, int y, char[] chars) {
        super(x, y);
        if(chars.length != 2) throw new IllegalArgumentException("Cells may only be represented by 2 characters");

        this.chars = chars;
    }
    @Override
    public boolean isFree() {
        return false;
    }

    @Override
    public char[] getChars() {
        return chars;
    }
}
