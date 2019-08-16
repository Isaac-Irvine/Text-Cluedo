package Game;

import java.util.*;

/**
 * Represents a player in the game.
 * Has an ID, a suspect that they control and a list of cards.
 */
public class Player {

    public enum PlayerState {
        WAITING, MOVING, FINISHED, NOT_TURN
    }

    private int num;
    private Game game;
    private Suspect suspect;
    private Scanner scanner;
    private boolean hasAccused;
    private List<Card> cards;
    private PlayerState currentState;

    // movement
    private int movesLeft = 0;
    private Set<Cell> visitedCells = new HashSet<>();

    /**
     * Initialize a player object
     *
     * @param game
     * @param scanner the scanner for input. Use System.in for user-input
     * @param num
     * @param suspect
     */
    public Player(Game game, Scanner scanner, int num, Suspect suspect) {
        this.game = game;
        this.num = num;
        this.scanner = scanner;
        this.suspect = suspect;
        this.hasAccused = false;
        this.currentState = PlayerState.NOT_TURN;
        cards = new ArrayList<>();
    }

    /**
     * Add a card to your hand
     *
     * @param card
     */
    public void addCard(Card card) {
        cards.add(card);
    }


    /**
     * Get a list of cards
     */
    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    /**
     * Return the current state of the player
     * @return
     */
    public PlayerState getCurrentState() {
        return currentState;
    }

    /**
     * Have your turn
     */
    /*public void turn() {

        boolean hasMoved = false;
        boolean finishedTurn = false;

        List<String> options = new ArrayList<String>() {{
            add("0: Roll Dice");
            add("1: Check Cards");
            add("2: Make Accusation");
            add("3: Quit Game");
        }};
        if (suspect.getCurrentRoom() != null) options.add("4: Make Suggestion");

        while (!finishedTurn) {
            System.out.println("\nChoose Action: " + options);
            System.out.print("Enter the number corresponding to your chosen action: ");
            String option = scanner.nextLine();

            // roll dice or finish turn (both will be 0)
            if (option.equals("0")) {
                // roll dice
                if (!hasMoved) {
                    int diceRoll = (int) (Math.random() * 6) + (int) (Math.random() * 6) + 2;
                    move(diceRoll);
                    hasMoved = true;
                    options.set(0, "0: Finish Turn");
                    if (suspect.getCurrentRoom() != null && options.size() <= 4) options.add("4: Make Suggestion");
                }
                // finish turn
                else {
                    finishedTurn = true;
                }
            }
            // check cards
            else if (option.equals("1")) {
                System.out.println("\nYour Cards: " + cards);
            }
            // accusation
            else if (option.equals("2")) {
                accuse();
                finishedTurn = true;
            } else if (option.equals("3")) {
                game.endGame(null);
                finishedTurn = true;
            }
            // suggestion (will finish your turn)
            else if (option.equals("4") && suspect.getCurrentRoom() != null) {
                suggest();
                finishedTurn = true;
            }
        }
    }*/

    /**
     * Have your turn
     */
    public void turn() {
        currentState = PlayerState.WAITING;
    }

    /**
     * Roll the dice
     */
    public int rollDice() {
        if (currentState != PlayerState.WAITING) return 0;

        currentState = PlayerState.MOVING;
        int diceRoll = (int) (Math.random() * 6) + (int) (Math.random() * 6) + 2;

        this.movesLeft = diceRoll;
        visitedCells = new HashSet<>();

        game.getGameView().updatePlayerState();

        return diceRoll;
    }

    /**
     * Move in a given direction
     *
     * @param direction
     */
    public void move(Cell.Direction direction) {
        if (currentState != PlayerState.MOVING) return;

        // check if move is valid
        if (suspect.getCurrentRoom() == null) {
            Set<Cell.Direction> directions = suspect.getAvaliableDirections(visitedCells);
            if (!directions.contains(direction)) return; // invalid direction

            visitedCells.add(suspect.getLocation());
            suspect.move(direction);

            move();
        }
    }

    /**
     * Move to a given cell
     *
     * @param cell
     */
    public void move(Cell cell) {
        if (currentState != PlayerState.MOVING) return;

        if (visitedCells.contains(cell)) return;

        // move to an adjacent cell
        if (suspect.getCurrentRoom() == null) {
            Set<Cell.Direction> directions = suspect.getAvaliableDirections(visitedCells);

            for (Cell.Direction direction : directions) {
                Cell neighbour = suspect.getBoard().getNeighbourCell(suspect.getLocation(), direction);

                // yes valid
                if (neighbour == cell) {
                    visitedCells.add(suspect.getLocation());
                    suspect.move(direction);

                    move();

                    return;
                }
            }
        }
        // move out of a room by clicking the exit
        else if (cell instanceof RoomEntranceCell) {
            RoomEntranceCell exit = (RoomEntranceCell) cell;

            List<RoomEntranceCell> cellList = suspect.getAvaliableRoomExits();

            if (cellList.contains(exit)) {
                visitedCells.add(exit);
                suspect.exitRoom(exit);
                move();
            }
        }
    }

    /**
     * Finish of a move
     * <p>
     * will change states if all moves left are used up
     */
    private void move() {
        movesLeft--;
        // STOP MOVING
        if (suspect.getCurrentRoom() != null || movesLeft <= 0 || suspect.getAvaliableDirections(visitedCells).size() == 0) {
            currentState = PlayerState.FINISHED;
            game.getGameView().updatePlayerState();
        }
        game.getGameView().repaint();
    }

    /**
     * Finish turn
     */
    public void finishTurn() {
        if (currentState != PlayerState.FINISHED) return;

        currentState = PlayerState.NOT_TURN;
        game.nextPlayer();
    }


    /**
     * Has accused
     *
     * @return
     */
    public boolean hasAccused() {
        return hasAccused;
    }

    /**
     * Make an accusation
     */
    public void accuse() {
        hasAccused = true;

        System.out.println("\nPick the circumstances of the murder correctly to win the game. Guess incorrectly and you will be out.\n");

        Card suspect = Game.getSuspectInput(scanner);

        System.out.println();

        Card weapon = Game.getWeaponInput(scanner);

        System.out.println();

        Card room = Game.getRoomInput(scanner);

        game.checkAccusation(this, suspect, weapon, room);
    }

    /**
     * Make a suggestion within a room
     */
    public void suggest() {
        if (suspect.getCurrentRoom() == null) throw new IllegalStateException("Cannot suggest if not in a room.");

        System.out.println("\nPick the circumstances of the murder.\n");

        Card otherSuspectCard = Game.getSuspectInput(scanner);
        Suspect otherSuspect = null;
        for (int i = 0; i < Game.allSuspects.length; i++) {
            if (otherSuspectCard.getName().equals(Game.allSuspects[i]))
                otherSuspect = suspect.getBoard().getSuspect(i);
        }
        if (otherSuspect == null) {
            throw new Error("Cant find that suspect in allSuspects");
        }

        System.out.println();

        Card weaponCard = Game.getWeaponInput(scanner);
        Weapon weapon = null;
        for (int i = 0; i < Game.allSuspects.length; i++) {
            if (weaponCard.getName().equals(Game.allWeapons[i]))
                weapon = suspect.getBoard().getWeapon(i);
        }
        if (weapon == null) {
            throw new Error("Cant find that suspect in allWeapons");
        }

        otherSuspect.enterRoom(suspect.getCurrentRoom());
        weapon.moveTo(suspect.getCurrentRoom().getAvailableCell());

        Card roomCard = new Card(suspect.getCurrentRoom().getName(), Card.CardType.ROOM);

        game.checkSuggestion(otherSuspectCard, weaponCard, roomCard);
    }

    /**
     * Let the player move a number of steps
     *
     * @param nSteps
     */
    public void move(int nSteps) {
        if (game != null) System.out.println("\nDice roll: " + nSteps);
        Set<Cell> visited = new HashSet<>();

        while (nSteps > 0) {
            if (game != null) game.draw();

            // regular move through free spaces
            if (suspect.getCurrentRoom() == null) {
                // get available directions
                Set<Cell.Direction> directions = suspect.getAvaliableDirections(visited);

                if (directions.size() == 0) {
                    if (game != null) System.out.println("You are unable to move further.");
                    break;
                }
                if (game != null) System.out.println("Moves left: " + nSteps);

                if (game != null) System.out.println("\nAvailable Directions: " + directions);
                if (game != null) System.out.print("Enter direction: ");

                // get choice
                Cell.Direction direction = Cell.Direction.getDirection(scanner.nextLine());

                while (direction == null || !directions.contains(direction)) {
                    if (game != null) System.out.print("Invalid Direction, Enter again: ");
                    direction = Cell.Direction.getDirection(scanner.nextLine());
                }

                // move
                visited.add(suspect.getLocation());
                suspect.move(direction);

                // stop moving if you reach a room
                if (suspect.getCurrentRoom() != null) {
                    break;
                }
            }
            // exit a room
            else {
                List<RoomEntranceCell> cellList = suspect.getAvaliableRoomExits();

                if (cellList.size() == 0) {
                    if (game != null) System.out.println("You are unable to exit the room as all exits are blocked.");
                    break;
                }

                // get the list of options to pick as a string
                List<String> options = new ArrayList<>();

                Cell.Direction previousDir = null;
                int doorCount = 0;

                for (RoomEntranceCell cell : cellList) {
                    // multiple doors with the same direction - add a number to the end
                    if (previousDir == cell.getDirection()) {
                        doorCount++;
                        options.add(options.size() + ": " + cell.getDirection() + " DOOR " + doorCount);
                    } else {
                        doorCount = 1;
                        previousDir = cell.getDirection();
                        options.add(options.size() + ": " + cell.getDirection() + " DOOR");
                    }

                }
                if (game != null) System.out.println("Moves left: " + nSteps);

                if (game != null) System.out.println("\nAvailable Exits: " + options);
                if (game != null) System.out.print("Enter the number corresponding to the door you want to exit: ");

                // get choice
                int choice = Game.getNumberInput(scanner, 0, cellList.size() - 1);

                // move
                RoomEntranceCell exit = cellList.get(choice);

                visited.add(exit);
                suspect.exitRoom(exit);
            }

            nSteps--;
        }

        if (game != null) game.draw();
        if (game != null) System.out.println("Movement finished.");
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


    /**
     * Game.Player string.
     *
     * @return
     */
    public String toString() {
        return "Player " + num + ": " + suspect.getChars()[0] + suspect.getChars()[1];
    }
}
