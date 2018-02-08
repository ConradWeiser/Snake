package game;

import javax.swing.*;

public class Board extends JPanel {

    //TODO: Allow for custom game dimensions
    //Set the board dimensions
    final private static int boardWidth = 1700;
    final private static int boardHeight = 980;

    //Set the pixel size for a node in the game, or the fruit piece
    final private static int pixelSize = 25;

    //Scale the amount of pixels the board can have.
    private final static int totalPixels = (boardWidth * boardHeight) / (pixelSize * pixelSize);

    //Is the game currently running?
    public static volatile boolean inGame = true;

    //A value used to set the game speed. A delay between each tick.
    private static int speed = 50;

    //The snake object running around at the moment
    private Snake snake;

}
