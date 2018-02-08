import enums.Direction;

import javax.swing.*;
import java.awt.*;
import java.util.PriorityQueue;
import java.util.Queue;

public class Core extends JFrame {

    //The queue holding all of the key-presses that the user makes while focused on the assets window
    final int QUEUE_CAPACITY = 3;
    public static Queue<Direction> keyQueue;

    //Variable holding the current instance of the board
    private static Board board;

    public Core() {

        //Initialize the queue as a priority queue, thus ensuring the order of which key-presses are taken in.
        keyQueue = new PriorityQueue<>(QUEUE_CAPACITY);

        //Create a new assets instance
        Core.board = new Board();

        add(Core.board);
        setResizable(false);
        pack();
        setTitle("Snaaaaakke");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Spawn the assets speed manager thread
        (new Thread(new GameSpeedManager())).start();

    }

    public static void runNextGameFrame() {

        board.runNextGameFrame();

    }

    public static void main(String[] args) {

        //Create the game
        EventQueue.invokeLater(() -> {
            JFrame game = new Core();
            game.setVisible(true);
        });
    }
}
