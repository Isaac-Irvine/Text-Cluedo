import java.io.File;
import java.util.*;

public class Game {
	private Board board;
	private List<Player> players;
	private int currentTurn;

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

		run();
	}

	/**
	 * Run the game
	 */
	public void run() {
		for(int i = 0; i < 6; i++) { // TODO implement proper game loop
			draw();
			Player player = players.get(currentTurn);

			System.out.println(player);

			player.turn();

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
}
