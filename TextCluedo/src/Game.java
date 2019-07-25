import java.util.List;

public class Game {
	private Board board;
	private List<Player> players;

	public Game(int nPlayers) {
		for(int i = 0; i < nPlayers; i++) {
			players.add(new Player());
		}
	}
}
