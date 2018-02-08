import assets.Snake;
import enums.Direction;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Board extends JPanel {

    //TODO: Allow for custom assets dimensions
    //Set the board dimensions pixel-wise
    final private static int boardWidth = 1700;
    final private static int boardHeight = 980;

    //Set the amount of cells spanning across the horizontal and vertical (Default: 64 --> 64x64 grid)
    //This number better be divisible by 2. You're gonna have a bad time otherwise
    final private static int boardCells = 64;

    //Set the pixel size for a node in the assets, or the fruit piece
    final private static int pixelSizeHeight = boardHeight / boardCells;
    final private static int pixelSizeWidth = boardWidth / boardCells;

    //Scale the amount of pixels the board can have.
    private final static int totalPixels = (boardWidth * boardHeight) / (pixelSizeHeight * pixelSizeWidth);

    //Is the assets currently running?
    public static volatile boolean inGame = true;

    //A value used to set the assets speed. A delay between each tick.
    private static int speed = 50;

    //The snake object running around at the moment
    private Snake snake;

    private Direction currentDirection;

    //An array representation of the game
    protected int[][] gameBoard;

    public Board() {

        //Init the user view panel
        this.setBackground(Color.GRAY);
        this.setFocusable(true);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
        requestFocus();

        this.setPreferredSize(new Dimension(boardWidth, boardHeight));

        //Attach the key listener
        this.addKeyListener(new KeyManager());

        //Initialize the game
        setupGame();
    }

    private void setupGame() {

        //Create the array representing the game board to the size specified and fill it with zeroes
        gameBoard = new int[boardCells][boardCells];


    }

    private void startGame() {

        //By default, set the snakes movement to be to the right
        currentDirection = Direction.RIGHT;
    }

    public void runNextGameFrame() {

        //Calculate snake movement
    }

}
