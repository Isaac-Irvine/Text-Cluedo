public abstract class Cell {
    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }


    private final int x;
    private final int y;
    private final char[] chars;
    private boolean free;
    private Entity entity;

    /**
     * Constructor for a cell object
     *
     * @param x     x location
     * @param y     y location
     * @param chars default characters to represent the cell
     * @param free  if the cell can be moved onto
     */
    public Cell(int x, int y, char[] chars, boolean free) {
        this.x = x;
        this.y = y;
        if (chars.length != 2) throw new IllegalArgumentException("Cells may only be represented by 2 characters.");
        this.chars = chars;
        this.free = free;
        this.entity = null;
    }


    /**
     * Get the characters at this
     *
     * @return
     */
    public char[] getChars() {
        return entity == null ? chars : entity.getChars();
    }

    /**
     * Set the entity at this cell
     */
    public void setEntity(Entity entity) {
        if (!isFree() || entity != null) throw new IllegalStateException("Cannot move an entity into an occupied cell.");
        this.entity = entity;
    }

    /**
     * If you can walk to this cell
     *
     * @return
     */
    public boolean isFree() {
        return free && entity == null;
    }

    /**
     * X
     */
    public int getX() {
        return x;
    }
    /**
     * Y
     */
    public int getY() {
        return y;
    }
    
    /**
     * entity
     */
    public Entity getEntity() {
        return entity;
    }

}