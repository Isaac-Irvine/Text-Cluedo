package Game;

import java.util.Scanner;

/**
 * Main class (Entry point)
 */
public class Cluedo {

	/**
	 * Entry point
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter the number of players (3-6): ");

		int nPlayers = Game.getNumberInput(scan, 3, 6);

		new Game(nPlayers);
	}
}
