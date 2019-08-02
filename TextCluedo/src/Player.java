import java.util.*;

public class Player {
	private int num;
	private Game game;
	private Suspect suspect;
	private boolean hasAccused;
	private List<Card> cards;

	public Player(Game game, int num, Suspect suspect) {
		this.game = game;
		this.num = num;
		this.suspect = suspect;
		this.hasAccused = false;
		cards = new ArrayList<>();
	}

	public void addCard(Card card) {
		cards.add(card);
	}


	/**
	 * Have your turn
	 */
	public void turn() {
		int diceRoll = (int) (Math.random() * 6) + (int) (Math.random() * 6) + 2;
		System.out.println("Dice roll: " + diceRoll);

		move(diceRoll);
	}

	/**
	 * Make an accusation
	 */
	public void accuse() {

	}

	public void suggest() {

	}

	/**
	 * Let the player move a number of steps
	 *
	 * @param nSteps
	 */
	public void move(int nSteps) {
		Set<Cell> visited = new HashSet<>();

		Scanner scan = new Scanner(System.in);

		while (nSteps > 0) {
			System.out.println("Moves left: " + nSteps);
			Set<Cell.Direction> directions = suspect.getAvaliableDirections(visited);

			if (directions.size() == 0) {
				System.out.println("You are unable to move further.");
				break;
			}

			System.out.println("Available Directions: " + directions);

			System.out.print("Enter direction: ");


			Cell.Direction direction = Cell.Direction.getDirection(scan.nextLine());

			while (direction == null || !directions.contains(direction)) {
				System.out.print("Invalid Direction, Enter again: ");
				direction = Cell.Direction.getDirection(scan.nextLine());
			}

			suspect.move(direction);
			game.draw();

			nSteps--;
		}
	}

	/**
	 * If the player has a card
	 *
	 * @param card
	 * @return
	 */
	public boolean hasCard(Card card) {
		return cards.contains(card);
	}


	public String toString() {
		return "Player " + num + ": " + suspect.getChars()[0] + suspect.getChars()[1] + "  " + cards;
	}
}
