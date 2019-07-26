import java.util.ArrayList;
import java.util.List;

public class Game {
	private Board board;
	private List<Player> players;

	/**
	 * Initialize a new game
	 * @param nPlayers
	 */
	public Game(int nPlayers) {
		players = new ArrayList<>();

		for(int i = 0; i < nPlayers; i++) {
			players.add(new Player());
		}
		board = new Board();

		draw();
	}

	/**
	 * Draw the board
	 */
	public void draw() {
		System.out.print(board.toString());
	}
}
