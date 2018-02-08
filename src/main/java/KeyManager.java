import enums.Direction;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

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

        System.err.println("OHH YES");
        Core.keyQueue.add(keyPress);

    }
}
