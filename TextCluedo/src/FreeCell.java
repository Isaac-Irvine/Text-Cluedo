public class FreeCell extends Cell {
    @Override
    public boolean isFree() {
        return true;
    }

    @Override
    public char[] getChars() {
        return new char[]{' ', ' '};
    }
}
