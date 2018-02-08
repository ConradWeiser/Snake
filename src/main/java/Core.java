import enums.Direction;
import game.Board;

import javax.swing.*;
import java.util.PriorityQueue;
import java.util.Queue;

public class Core extends JFrame {

    //The queue holding all of the key-presses that the user makes while focused on the game window
    final int QUEUE_CAPACITY = 3;
    public static Queue<Direction> keyQueue;

    //Variable holding the current instance of the board
    private static Board board;

    public Core() {

        //Initialize the queue as a priority queue, thus ensuring the order of which key-presses are taken in.
        keyQueue = new PriorityQueue<>(QUEUE_CAPACITY);

        //Create a new game instance
        Core.board = new Board();

        add(Core.board);
        setResizable(false);
        pack();
        setTitle("Snaaaaakke");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Spawn the game speed manager thread
        (new Thread(new GameSpeedManager())).start();

    }

    public static void runNextGameFrame() {

        //Do things here

    }
}
