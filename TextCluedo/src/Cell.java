public abstract class Cell {
    private final int x;
    private final int y;
    private final char[] originalChars;
    private char[] chars;
    private boolean free;

    public Cell(int x, int y, char[] chars, boolean free) {
        this.x = x;
        this.y = y;
        if(chars.length != 2) throw new IllegalArgumentException("Cells may only be represented by 2 characters");
        this.originalChars = chars;
        this.chars = chars;
        this.free = free;
    }

    private Item item = null;

    public char[] getChars() {
        return chars;
    }

    private void resetChars() {
        chars = originalChars;
    }

    protected void setChars(char[] newChars) {
    	if(chars.length != 2) throw new IllegalArgumentException("Cells may only be represented by 2 characters");
        chars = newChars;
    }

    public boolean isFree() {
        return free;
    }
}
