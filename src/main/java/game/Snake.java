package game;

import enums.Direction;

import java.awt.*;
import java.util.Vector;

import static enums.Direction.RIGHT;

public class Snake {

    /**
     * Variable storing what direction the game is currently going
     */
    private Direction snakeDirection;

    /**
     * Variable keeping track of each individual joint or 'dot' making up the snake
     */
    private List<Joint> snakeJoints;

    /**
     * Constructor requiring the board dimensions, in order to ensure that the snake is spawned in bounds
     */
    public Snake(Dimension boardDimensions, int pixelSize) {



    }
}
