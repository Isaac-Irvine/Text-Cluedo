import java.util.Scanner;

public class Cluedo {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of players (3-6):");

		int nPlayers = scan.nextInt();

		new Game(nPlayers);
	}
}
