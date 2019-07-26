import java.util.ArrayList;
import java.util.List;

public class Board {
	private static final int WIDTH = 26;
	private static final int HEIGHT = 27;

	private Cell[][] cells;
	private List<Suspect> allSuspects;
	private List<Room> allRooms;
	private List<Weapon> allWeapons;

	/**
	 * Initialize the board.
	 * <p>
	 * TODO: Load from file instead of hardcoded
	 */
	public Board() {
		allSuspects = new ArrayList<>();
		allRooms = new ArrayList<>();
		allWeapons = new ArrayList<>();

		cells = new Cell[WIDTH][HEIGHT];

		// default all cells are empty
		for (int x = 0; x < WIDTH; x++) {
			for (int y = 0; y < WIDTH; y++) {
				cells[x][y] = new FreeCell(x, y);
			}
		}


		// outside wall
		cells[0][0] = new WallCell(0, 0, new char[]{'+', '+'});
		cells[WIDTH - 1][0] = new WallCell(0, 0, new char[]{'+', '+'});
		cells[0][HEIGHT - 1] = new WallCell(0, 0, new char[]{'+', '+'});
		cells[WIDTH - 1][HEIGHT - 1] = new WallCell(0, 0, new char[]{'+', '+'});
		for (int x = 1; x < WIDTH - 1; x++) {
			cells[x][0] = new WallCell(x, 0, new char[]{'-', '-'});
			cells[x][HEIGHT - 1] = new WallCell(x, HEIGHT - 1, new char[]{'-', '-'});
		}
		for (int y = 1; y < HEIGHT - 1; y++) {
			cells[0][y] = new WallCell(0, y, new char[]{'|', '|'});
			cells[WIDTH - 1][y] = new WallCell(WIDTH - 1, y, new char[]{'|', '|'});
		}

		// cellar
		for (int x = 11; x < 16; x++) {
			for (int y = 11; y < 18; y++) {
				cells[x][y] = new WallCell(x, y,new char[]{'+','+'});
			}
		}

		// kitchen
		Room kitchen = new Room();
	}


	/**
	 * The entire board as a string
	 *
	 * @return
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				Cell cell = cells[x][y];
				sb.append(cell.getChars()[0]);
				sb.append(cell.getChars()[1]);
			}
			sb.append('\n');
		}
		return sb.toString();
	}
}
