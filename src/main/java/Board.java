import assets.Joint;
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
    public static volatile boolean inGame = false;

    //A value used to set the assets speed. A delay between each tick.
    private static int speed = 50;

    //The snake object running around at the moment
    private Snake snake;

    private Direction currentDirection;

    //An array representation of the game
    public int[][] gameBoard;

    public Board() {

        //Init the user view panel
        //this.setBackground(Color.GRAY);
        //this.setFocusable(true);
        //this.setFocusable(true);
        //this.setFocusTraversalKeysEnabled(false);
        //requestFocus();

        //this.setPreferredSize(new Dimension(boardWidth, boardHeight));

        //Attach the key listener
        this.addKeyListener(new KeyManager());

        //Initialize the game
        setupGame();
    }

    private void setupGame() {

        //Create the array representing the game board to the size specified and fill it with zeroes
        gameBoard = new int[boardCells][boardCells];

        //SNAAAAAKKEE
        this.snake = new Snake(boardCells, gameBoard);
        startGame();

    }

    private void startGame() {

        //By default, set the snakes movement to be to the right
        currentDirection = Direction.RIGHT;
        inGame = true;
    }

    public void runNextGameFrame() {

        //Only run if the game is started
        if(inGame) {

            //Calculate snake movement
            snake.moveSnake(gameBoard);

            //If the snake is touching the wall of death (the last cell in each array) game over
            for(int i = 0; i < boardCells; i++) {

                if(gameBoard[0][i] == 1 || gameBoard[i][0] == 1) {
                    inGame = false;
                    gameOver();
                }
            }


            //Debug
/*            for(int y = 0; y < boardCells; y++ ) {

                for(int x = 0; x < boardCells; x++) {

                    System.out.print(gameBoard[x][y] + "|");
                    if(x == boardCells - 1)
                        System.out.print("\n");
                }
            }
            */
        }

    }

    private void gameOver() {

        //Game over logic here
    }

}
