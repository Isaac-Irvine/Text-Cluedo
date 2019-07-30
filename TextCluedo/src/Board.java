import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Board {
    private final String roomsFileName = "rooms.txt";
    private final String freeSpacesFileName = "free spaces.txt";
    private final String startingPlacesFileName = "starting places.txt";
    private final String boardFileName = "board.txt";

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
        try {
        	Scanner roomsReader = new Scanner(roomsFile);
            while (roomsReader.hasNext()) {
            	roomsReader.next();
            }
            
            roomsReader.close();
        } catch (IOException e) {
            throw new Error(e);
        }
        
        /*

        char[][] walls = new char[height][width]; // 1 char per cell, represents if a wall or a free cell or a room.
        char[][][] boardString = new char[height][width][2]; // 2 chars per cell
        cells = new Cell[height][width];

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
