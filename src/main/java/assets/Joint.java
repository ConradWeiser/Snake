package assets;

import enums.Direction;

public class Joint {

    //Specifies a direction which the joint is currently moving in
    Direction jointDirection;

    //Specifies an x-point in the 2d array
    private int x;

    //specifies an y-point in the 2d array
    private int y;

    public Joint(int x, int y, Direction defaultDirection) {

        this.x = x;
        this.y = y;
        this.jointDirection = defaultDirection;
    }

    public int getX() { return x; }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getJointDirection() {
        return jointDirection;
    }

    private boolean isDirectionOpposite(Direction current, Direction change) {

        if(current == Direction.DOWN && change == Direction.UP) {
            return true;
        }

        else if(current == Direction.UP && change == Direction.DOWN) {
            return true;
        }
        else if(current == Direction.RIGHT && change == Direction.LEFT) {
            return true;
        }
        else if(current == Direction.LEFT && change == Direction.RIGHT) {
            return true;
        }
        else {
            return false;
        }
    }

    public void setJointDirection(Direction jointDirection) {

        //Verify that the direction isn't opposite of what we're going currently. If so, ignore it
        if(!isDirectionOpposite(this.jointDirection, jointDirection))
            this.jointDirection = jointDirection;
    }
}
