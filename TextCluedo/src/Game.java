import java.io.File;
import java.util.*;

public class Game {
	private Board board;
	private List<Player> players;

	private static final String[] allSuspects = new String[]
			{"Miss Scarlett", "Col. Mustard", "Mrs. White", "Mr. Green", "Mrs. Peacock", "Prof. Plum"};
	private static final String[] allWeapons = new String[]
			{"Candlestick", "Dagger", "Lead Pipe", "Revolver", "Rope", "Spanner"};
	private static final String[] allRooms = new String[]
			{"Kitchen", "Ball Room", "Conservatory", "Billiard Room", "Library", "Study", "Hall", "Lounge"};

	private Card murderer, weapon, room;

	/**
	 * Initialize a new game
	 *
	 * @param nPlayers
	 */
	public Game(int nPlayers) {
		if(nPlayers > allSuspects.length || nPlayers < 3)
			throw new IllegalArgumentException("Invalid number of players.");

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
			System.out.print("Player " + p + ", please pick your character\n[");
			for (int i = 0; i < charactersLeft.size(); i++) {
				System.out.print(charactersLeft.get(i));
				if(i != charactersLeft.size()-1) System.out.print(", ");
			}
			System.out.print("]: ");

			//get the picked option
			String picked = scan.nextLine();
			while(!charactersLeft.contains(picked)) {
				System.out.print("Sorry, that is not one of the characters you can pick. Enter again: ");
				picked = scan.nextLine();
			}

			// add the player
			Player player = new Player(p, picked);
			charactersLeft.remove(picked);
			players.add(player);
		}

		// give cards to players
		Collections.shuffle(playerCards);

		for(int i = 0, p = 0; i < playerCards.size(); i++, p++, p%=nPlayers) {
			Player player = players.get(p);
			player.addCard(playerCards.get(i));
		}

		for(Player player : players) {
			System.out.println(player);
		}
		System.out.println(weapon.getName() + ".." + room.getName() + ".." + murderer.getName());


		board = new Board("map/", 26, 27);

		draw();
	}

	/**
	 * Draw the board
	 */
	public void draw() {
		System.out.print(board.toString());
	}
}
