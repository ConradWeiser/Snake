import enums.Direction;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    //Default to right, as that is the direction we start moving
    private Direction previousDirectionInput = Direction.RIGHT;

    @Override
    public void keyTyped(KeyEvent e) {

        //Unused, this acts weird when the key is held down
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch(e.getKeyChar()) {

            case 'w': addKeyToGameBuffer(Direction.UP); break;
            case 's': addKeyToGameBuffer(Direction.DOWN); break;
            case 'a': addKeyToGameBuffer(Direction.LEFT); break;
            case 'd': addKeyToGameBuffer(Direction.RIGHT); break;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        //Unused, we are using on key press
    }

    private void addKeyToGameBuffer(Direction keyPress) {

        if(previousDirectionInput != keyPress)
            Core.keyQueue.add(keyPress);

        previousDirectionInput = keyPress;

    }
}
