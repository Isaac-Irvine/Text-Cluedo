import java.util.List;

public class Room {
    private List<RoomEntityCell> roomCells;
    private List<RoomEntranceCell> roomEntrances;
    private String name;
    
    public Room(String name) {
    	this.name = name;
    }


    public Cell getFreeCell() {
        return null;
    }
}
