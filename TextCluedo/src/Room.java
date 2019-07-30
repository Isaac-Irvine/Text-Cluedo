import java.util.List;

public class Room {
    private List<RoomEntityCell> roomCells;
    private List<RoomEntranceCell> roomEnnametrances;
    private String name;
    
    public Room(String name) {
    	this.name = name;
    }
}
