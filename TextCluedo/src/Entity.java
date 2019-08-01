public abstract class Entity {
    private Cell location;
    private Board board;
    private Card card;
    private char[] chars;

    public Card getCard() {
        return card;
    }

    /**
     * Moves the entity to a given cell
     * @cell the cell to move to
     */
    public void moveTo(Cell cell) {
        location.setEntity(null);
        cell.setEntity(this);
    }

    public char[] getChars() {
        return chars;
    }

}
