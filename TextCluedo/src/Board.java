import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Board {
    private static final String roomsFileName = "rooms.txt";
    private static final String freeSpacesFileName = "free spaces.txt";
    private static final String startingPlacesFileName = "starting places.txt";
    private static final String boardFileName = "board.txt";

    private final int width;
    private final int height;
    private Cell[][] cells;
    private List<Suspect> allSuspects;
    private List<Room> allRooms;
    private List<Weapon> allWeapons;

    /**
     * Initialize the board by loading from a file.
     */
    public Board(String dir, int width, int height) {
        this.width = width;
        this.height = height;

        // load rooms
        File roomsFile = new File(dir + roomsFileName);
        File freeSpacesFile = new File(dir + freeSpacesFileName);
        File startingPlacesFile = new File(dir + startingPlacesFileName);
        File boardFile = new File(dir + boardFileName);


        // creates all cells

        cells = new Cell[height][width];

        // FIXME: Remove this test part.
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                cells[y][x] = new FreeCell(x, y);
            }
        }


        try {
            // #1 load all rooms

            Scanner roomsScanner = new Scanner(roomsFile);
            HashSet<Room> rooms = new HashSet<>();
            while (roomsScanner.hasNext()) {
                String token = roomsScanner.next();
                // skip comments
                if (token.startsWith("#")) {
                    roomsScanner.nextLine();
                    continue;
                } else if (token.equals("name")) {
                    roomsScanner.next(); // skipping '='
                    Room room = new Room(roomsScanner.next()); // name
                } else {
                    throw new Error("Unknown token in rooms File: " + token);
                }
            }

            roomsScanner.close();


            // #2 load board.txt into an array (I've done this before commented out)

            // #3 create the cell remaining cell objects (that aren't Room objects) using free spaces.txt

            // #4 create all the suspect objects and load them into the correct cells using setEntity

            // #5 create all the weapon objects and add them to rooms randomly as per the brief.
        } catch (IOException e) {
            throw new Error(e);
        }
        


        /*

        char[][] walls = new char[height][width]; // 1 char per cell, represents if a wall or a free cell or a room.
        char[][][] boardString = new char[height][width][2]; // 2 chars per cell
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;

            // process the board string
            for (int y = 0; y < height; y++) {
                line = reader.readLine();
                for (int x = 0; x < width; x++) {
                    boardString[y][x][0] = line.charAt(x * 2);
                    boardString[y][x][1] = line.charAt(x * 2 + 1);
                }
            }

            // process the walls/floor
            for (int y = 0; y < height; y++) {
                line = reader.readLine();
                for (int x = 0; x < width; x++) {
                    walls[y][x] = line.charAt(x);
                }
            }
            reader.close();

        } catch (IOException e) {
            throw new Error(e);
        }

        // create all the cells
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // get cell type at this position
                char cellType = walls[y][x];

                // create cell object
                Cell cell;

                switch (cellType) {
                    case 'X':
                    case '#':
                        cell = new WallCell(x, y, new char[]{boardString[y][x][0], boardString[y][x][1]});
                        break;
                    default:
                        cell = new FreeCell(x, y);
                }

                cells[y][x] = cell;
            }
        }
        */
    }

    /**
     * Get a set of all the avaliable directions coming from this cell
     *
     * @param cell
     * @return
     */
    public Set<Cell.Direction> getAvaliableNeighbours(Cell cell) {
        Set<Cell.Direction> directions = new HashSet<>();

        for (Cell.Direction d : Cell.Direction.values()) {
            Cell neighbour = getNeighbourCell(cell, d);
            if (neighbour == null) continue;
            if (neighbour.isFree()) directions.add(d);
        }

        return directions;
    }


    /**
     * Get the cell in the given direction
     * Null if there is no cell in that direction
     *
     * @param direction
     * @return
     */
    public Cell getNeighbourCell(Cell cell, Cell.Direction direction) {
        int x = cell.getX();
        int y = cell.getY();

        switch (direction) {
            case LEFT:
                x--;
                break;
            case RIGHT:
                x++;
                break;
            case UP:
                y--;
                break;
            case DOWN:
                y++;
                break;
        }

        if (x < 0 || x >= width || y < 0 || y >= height) return null;
        else return cells[y][x];
    }


    /**
     * The entire board as a string
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Cell cell = cells[y][x];
                sb.append(cell.getChars()[0]);
                sb.append(cell.getChars()[1]);
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
