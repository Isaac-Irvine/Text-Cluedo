package Game;

import java.util.Scanner;

public class Cluedo {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter the number of players (3-6): ");

		int nPlayers = Game.getNumberInput(scan, 3, 6);

		new Game(nPlayers);
	}
}
