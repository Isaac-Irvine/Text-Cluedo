import java.io.File;
import java.util.*;

public class Game {
	private Board board;
	private List<Player> players;
	private int currentTurn;
	private int incorrectGuesses;
	private boolean gameOver;

	public static final String[] allSuspects = new String[]
			{"Miss Scarlett", "Col. Mustard", "Mrs. White", "Mr. Green", "Mrs. Peacock", "Prof. Plum"};
	public static final String[] allWeapons = new String[]
			{"Candlestick", "Dagger", "Lead Pipe", "Revolver", "Rope", "Spanner"};
	public static final String[] allRooms = new String[]
			{"Kitchen", "Ball Room", "Conservatory", "Dining Room", "Billiard Room", "Lounge", "Hall", "Study", "Library"};

	// easy way to look up the index of a suspect in the array. Contains some aliases
	public static final Map<String, Integer> suspectAliases = new HashMap<String, Integer>() {{
		put("miss scarlett", 0); put("scarlett", 0); put("ms", 0);
		put("col mustard", 1); put("col. mustard", 1); put("mustard", 1); put("cm", 1);
		put("mrs. white", 2); put("mrs white", 2); put("white", 2); put("mw", 2);
		put("mr. green", 3);put("mr green", 3);put("green", 3);put("mg", 3);
		put("mrs. peacock", 4); put("mrs peacock", 4); put("peacock", 4); put("mp", 4);
		put("prof. plum", 5); put("prof plum", 5); put("plum", 5); put("pp", 5);
	}};

	private Card murderer, weapon, room;

	/**
	 * Initialize a new game
	 *
	 * @param nPlayers
	 */
	public Game(int nPlayers) {
		if (nPlayers > allSuspects.length || nPlayers < 3)
			throw new IllegalArgumentException("Invalid number of players.");


		// create the board
		board = new Board("map/", 26, 27);
		currentTurn = 0;
		gameOver = false;
		incorrectGuesses = 0;

		players = new ArrayList<>();
		List<Card> playerCards = new ArrayList<>();

		// pick the murder circumstances randomly
		// choose suspect
		int randomSuspect = (int) (Math.random() * allSuspects.length);
		for (int i = 0; i < allSuspects.length; i++) {
			Card card = new Card(allSuspects[i], Card.CardType.SUSPECT);
			if (i == randomSuspect)
				murderer = card;
			else
				playerCards.add(card);
		}
		// choose room
		int randomRoom = (int) (Math.random() * allRooms.length);
		for (int i = 0; i < allRooms.length; i++) {
			Card card = new Card(allRooms[i], Card.CardType.ROOM);
			if (i == randomRoom)
				room = card;
			else
				playerCards.add(card);
		}
		// choose weapon
		int randomWeapon = (int) (Math.random() * allWeapons.length);
		for (int i = 0; i < allWeapons.length; i++) {
			Card card = new Card(allWeapons[i], Card.CardType.WEAPON);
			if (i == randomWeapon)
				weapon = card;
			else
				playerCards.add(card);
		}

		// let players decide their characters
		Scanner scan = new Scanner(System.in);

		List<String> charactersLeft = new ArrayList<>(Arrays.asList(allSuspects));

		// add all players
		for (int p = 1; p <= nPlayers; p++) {
			System.out.print("\nPlayer " + p + ", please pick your character\n[");
			for (int i = 0; i < charactersLeft.size(); i++) {
				System.out.print(i + ": " + charactersLeft.get(i));
				if (i != charactersLeft.size() - 1) System.out.print(", ");
			}
			System.out.print("]: ");

			//get the picked option
			String picked;
			while (true) {
				picked = scan.nextLine().toLowerCase();
				if (suspectAliases.containsKey(picked)) {
					break;
				}
				try {
					int num = Integer.parseInt(picked);
					if (num >= 0 && num < charactersLeft.size()) {
						picked = charactersLeft.get(num);
						break;
					}
				} catch (NumberFormatException e) {
				}
				System.out.print("Sorry, that is not one of the characters you can pick. Enter again: ");
			}

			int index = suspectAliases.get(picked.toLowerCase());

			// add the player
			Player player = new Player(this, scan, p, board.getSuspect(index));
			charactersLeft.remove(allSuspects[index]);
			players.add(player);
		}

		// give cards to players
		Collections.shuffle(playerCards);

		for (int i = 0, p = 0; i < playerCards.size(); i++, p++, p %= nPlayers) {
			Player player = players.get(p);
			player.addCard(playerCards.get(i));
		}

		// run the game loop
		run();
	}

	/**
	 * Run the game
	 */
	public void run() {
		while(!gameOver) {
			draw();
			Player player = players.get(currentTurn);

			if (!player.hasAccused()) {
				System.out.println(player);

				player.turn();
			}

			currentTurn++;
			currentTurn %= players.size();
		}
	}

	/**
	 * Draw the board
	 */
	public void draw() {
		System.out.print("\n" + board.toString());
	}

	/**
	 * Check if an accusation is correct for a given player
	 */
	public void checkAccusation(Player player, Card suspect, Card weapon, Card room) {
		if(suspect.equals(this.murderer) && weapon.equals(this.weapon) && room.equals(this.room)) {
			endGame(player);
		} else {
			incorrectGuesses++;
			System.out.println("\n" + player + " has accused incorrectly, so is out of the game!");
			if(incorrectGuesses == players.size()) endGame(null);
		}
	}

	/**
	 * End the game by printing the
	 * @param correctGuess non-null will imply this person has won the game.
	 */
	public void endGame(Player correctGuess) {
		if(correctGuess != null) System.out.println("\n" + correctGuess + " has accused correctly and has won the game!");
		else System.out.println("\nNo one managed to accuse the murder correctly, so the game is over.");
		System.out.println("\nMurderer: " + murderer.getName());
		System.out.println("Weapon: " + weapon.getName());
		System.out.println("Room: " + room.getName());

		gameOver = true;
	}

	/**
	 * Get suspect card input
	 * @param scanner
	 * @return
	 */
	public static Card getSuspectInput(Scanner scanner) {
		System.out.print("Suspects: [");
		for (int i = 0; i < allSuspects.length; i++) {
			System.out.print(i + ": " + allSuspects[i]);
			if (i != allSuspects.length - 1) System.out.print(", ");
		}
		System.out.println("]");

		System.out.print("Enter the number corresponding to the suspect you pick: ");
		int index = getNumberInput(scanner, 0, allSuspects.length - 1);

		String name = allSuspects[index];
		Card card = new Card(name,Card.CardType.SUSPECT);

		return card;
	}

	/**
	 * Get weapon card input
	 * @param scanner
	 * @return
	 */
	public static Card getWeaponInput(Scanner scanner) {
		System.out.print("Weapons: [");
		for (int i = 0; i < allWeapons.length; i++) {
			System.out.print(i + ": " + allWeapons[i]);
			if (i != allWeapons.length - 1) System.out.print(", ");
		}
		System.out.println("]");

		System.out.print("Enter the number corresponding to the weapon you pick: ");
		int index = getNumberInput(scanner, 0, allWeapons.length - 1);

		String name = allWeapons[index];
		Card card = new Card(name,Card.CardType.WEAPON);

		return card;
	}

	/**
	 * Get weapon card input
	 * @param scanner
	 * @return
	 */
	public static Card getRoomInput(Scanner scanner) {
		System.out.print("Rooms: [");
		for (int i = 0; i < allRooms.length; i++) {
			System.out.print(i + ": " + allRooms[i]);
			if (i != allRooms.length - 1) System.out.print(", ");
		}
		System.out.println("]");

		System.out.print("Enter the number corresponding to the room you pick: ");
		int index = getNumberInput(scanner, 0, allRooms.length - 1);

		String name = allRooms[index];
		Card card = new Card(name,Card.CardType.ROOM);

		return card;
	}

	/**
	 * Get a number input
	 * @return
	 */
	public static int getNumberInput(Scanner scanner, int min, int max) {
		while (true) {
			String line = scanner.nextLine();

			try {
				int choice = Integer.parseInt(line);
				if (choice < min || choice > max) {
					System.out.print("Invalid option, Enter again: ");
					continue;
				}

				return choice;
			} catch (NumberFormatException e) {
				System.out.print("Invalid option, Enter again: ");
			}
		}
	}
}
