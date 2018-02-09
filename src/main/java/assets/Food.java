package assets;

import java.util.Random;

/**
 * Created by Conrad on 2/9/2018.
 */
public class Food {

    //X and Y coordinates in the 2d array of where the food is currently located
    private int x;
    private int y;

    public void createFood(int boardLength, Snake currentSnake, int[][] gameBoard) {

        //Generate a random location for the food
        Random random = new Random();

        //Generate an x/y location. Bounded by the length -2 to prevent fruit from spawning on the outside. +1 to avoid left edge clipping
        this.x = random.nextInt(boardLength - 2) + 1;
        this.y = random.nextInt(boardLength - 2) + 1;

        //If the new x and y position matches a location of a snake joint, regenerate a new position
        for(Joint currentJoint: currentSnake.getSnakeJoints()) {

            if(currentJoint.getX() == this.x && currentJoint.getY() == this.y) {
                createFood(boardLength, currentSnake, gameBoard);
                break;
            }
        }

        //At this point, we know that the food is in a unique spot on the board. Add it.
        addFoodToBoard(gameBoard);
    }

    private void addFoodToBoard(int[][] gameBoard) {

        gameBoard[x][y] = 2;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
