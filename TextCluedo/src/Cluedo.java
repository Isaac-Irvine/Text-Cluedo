import java.util.Scanner;

public class Cluedo {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter the number of players (3-6): ");

		int nPlayers = scan.nextInt();

		while(nPlayers < 3 || nPlayers > 6) {
			System.out.print("Invalid number of players. Enter again: ");
			nPlayers = scan.nextInt();
		}

		new Game(nPlayers);
	}
}
