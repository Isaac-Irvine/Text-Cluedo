public class RoomCell extends Cell {
    @Override
    public boolean isFree() {
        return false;
    }

    @Override
    public char[] getChars() {
        return new char[]{' ', ' '};
    }
}
