import java.util.*;

public class Player {
    private int num;
    private Game game;
    private Suspect suspect;
    private Scanner scanner;
    private boolean hasAccused;
    private List<Card> cards;

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
     * Have your turn
     */
    public void turn() {

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
                    if (suspect.getCurrentRoom() != null && options.size() < 4) options.add("3: Make Suggestion");
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
        // TODO make accusation
        hasAccused = true;
        game.checkAccusation(this, null, null, null);
    }

    /**
     * Make a suggestion within a room
     */
    public void suggest() {
        if (suspect.getCurrentRoom() == null) throw new IllegalStateException("Cannot suggest if not in a room.");

    }

    /**
     * Let the player move a number of steps
     *
     * @param nSteps
     */
    public void move(int nSteps) {
        System.out.println("\nDice roll: " + nSteps);
        Set<Cell> visited = new HashSet<>();

        while (nSteps > 0) {
            game.draw();

            // regular move through free spaces
            if (suspect.getCurrentRoom() == null) {
                // get available directions
                Set<Cell.Direction> directions = suspect.getAvaliableDirections(visited);

                if (directions.size() == 0) {
                    System.out.println("You are unable to move further.");
                    break;
                }
                System.out.println("Moves left: " + nSteps);

                System.out.println("\nAvailable Directions: " + directions);
                System.out.print("Enter direction: ");

                // get choice
                Cell.Direction direction = Cell.Direction.getDirection(scanner.nextLine());

                while (direction == null || !directions.contains(direction)) {
                    System.out.print("Invalid Direction, Enter again: ");
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
                    System.out.println("You are unable to exit the room as all exits are blocked.");
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
                System.out.println("Moves left: " + nSteps);

                System.out.println("\nAvailable Exits: " + options);
                System.out.print("Enter the number corresponding to the door you want to exit: ");

                // get choice
                int choice = Game.getNumberInput(scanner, 0, cellList.size() - 1);

                // move
                RoomEntranceCell exit = cellList.get(choice);

                visited.add(exit);
                suspect.exitRoom(exit);
            }

            nSteps--;
        }

        game.draw();
        System.out.println("Movement finished.");
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
     * Player string.
     *
     * @return
     */
    public String toString() {
        return "Player " + num + ": " + suspect.getChars()[0] + suspect.getChars()[1];
    }
}
