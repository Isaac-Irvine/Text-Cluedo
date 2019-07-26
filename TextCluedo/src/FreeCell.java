public class FreeCell extends Cell {
    public FreeCell(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isFree() {
        return true;
    }

    @Override
    public char[] getChars() {
        return new char[]{' ', ' '};
    }
}
