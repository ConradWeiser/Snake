import assets.Food;
import assets.Joint;
import assets.Snake;
import enums.Direction;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Board extends JPanel {

    //TODO: Allow for custom assets dimensions
    //Set the board dimensions pixel-wise
    final private static int boardWidth = 980;
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

    //The snake object running around at the moment
    private Snake snake;

    //An array representation of the game
    public int[][] gameBoard;

    private Food currentFood;

    public Board() {

        //Init the user view panel
        this.setBackground(Color.DARK_GRAY);
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

        //SNAAAAAKKEE - Defaulting to the right direction
        this.snake = new Snake(boardCells, gameBoard, Direction.RIGHT);

        //Put some food somewhere
        currentFood = new Food();
        currentFood.createFood(boardCells, this.snake, gameBoard);

        //Debug
        for(int y = 0; y < boardCells; y++ ) {

            for(int x = 0; x < boardCells; x++) {

                System.out.print(gameBoard[x][y] + "|");
                if(x == boardCells - 1)
                    System.out.print("\n");
            }
        }

        //Print out where food currently is
        System.err.println("Food is currently at: (" + currentFood.getX() + "," + currentFood.getY() +")");

        startGame();

    }

    private void startGame() {

        inGame = true;

    }

    public void runNextGameFrame() {

        //Only run if the game is started
        if(inGame) {

            //Pop the next movement input by the user, and set the snake direction to it, if one exists
            Direction playerRequestedDirection = Core.keyQueue.poll();
            if(playerRequestedDirection != null)
                snake.getSnakeHead().setJointDirection(playerRequestedDirection);


            //If the snake is touching the wall of death (the last cell in each array) game over
            if(snake.getSnakeHead().getX() == 0 || snake.getSnakeHead().getX() == boardCells - 1 || snake.getSnakeHead().getY() == 0 || snake.getSnakeHead().getY() == boardCells - 1) {

                //Game over!
                inGame = false;
                gameOver();
                return;

            }

            //Calculate snake movement
            snake.moveSnake(gameBoard);

            //If the head is touching a fruit, add a new joint and create a new fruit
            if(currentFood.getX() == snake.getSnakeHead().getX() && currentFood.getY() == snake.getSnakeHead().getY()) {

                snake.createNewJoint();
                currentFood.createFood(boardCells, snake, gameBoard);
            }


            //The last thing we do is repaint the window
            this.repaint();
        }

    }




    private void gameOver() {

        //Game over logic here
    }

    /**
     * Function used to paint our images to the screen
     * @param g the graphics object to be painted
     */

    protected void paintComponent(Graphics g) {


        super.paintComponent(g);
        draw(g);
    }

    void draw(Graphics g) {

        //Color the fruit in
        g.setColor(Color.RED);

        g.fillRect((boardWidth / boardCells) * currentFood.getX(), (boardHeight / boardCells) * currentFood.getY(), pixelSizeWidth , pixelSizeHeight);

        //Draw the SNAAAAKKKEEE
        for(int i = 0; i < snake.getSnakeJoints().size(); i++) {

            //If we're drawing the snake head, set the color to black
            if(i == 0)
                g.setColor(Color.BLACK);

            else
                g.setColor(Color.BLUE);

            //Fill the rectangle for the sneek
            int jointX, jointY;
            jointX = snake.getSnakeJoints().get(i).getX();
            jointY = snake.getSnakeJoints().get(i).getY();

            g.fillRect((boardWidth / boardCells) * jointX, (boardHeight / boardCells) * jointY, pixelSizeWidth, pixelSizeHeight);
            
        }

        //Sync graphics together
        Toolkit.getDefaultToolkit().sync();
    }

}
