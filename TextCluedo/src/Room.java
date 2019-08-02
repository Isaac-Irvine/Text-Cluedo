import java.util.ArrayList;
import java.util.List;

public class Room {
    private List<RoomEntityCell> roomCells; // list of all the places things can be put in the room.
    private List<RoomEntranceCell> roomEntrances; // list of all the entrances to the room
    private String name;
    
    public Room(String name) {
        this.name = name;
        roomCells = new ArrayList<>();
        roomEntrances = new ArrayList<>();
    }


    /**
     * List of all the room entrances
     * @return
     */
    public List<RoomEntranceCell> getRoomEntrances() {
        return roomEntrances;
    }

    /**
     * Returns the first free room entity cell.
     * @return
     */
    public Cell getAvailableCell() {
        for (RoomEntityCell cell : roomCells) {
        	if (cell.getEntity() == null) {
        		return cell;
        	}
        }
        return null;
    }

    public void addEntityCell(RoomEntityCell cell) {
        roomCells.add(cell);
    }
    public void addEntranceCell(RoomEntranceCell cell) {
        roomEntrances.add(cell);
    }
}
