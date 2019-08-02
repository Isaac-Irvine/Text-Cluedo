public class Weapon extends Entity {
	public Weapon(Board board, Room room, char[] chars){
		super(board, room.getAvailableCell(), chars);
	}
}
