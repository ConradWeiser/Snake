package assets;

public class Joint {

    //Specifies an x-point in the 2d array
    private int x;

    //specifies an y-point in the 2d array
    private int y;

    public Joint(int x, int y) {

        this.x = x;
        this.y = y;
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
}
