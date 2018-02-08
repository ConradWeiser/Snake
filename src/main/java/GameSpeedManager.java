import javax.swing.*;
import java.awt.*;

public class GameSpeedManager extends Thread {

    //How many hertz is the assets running in?
    final double gameSpeed = 5.0;

    //Calculate how many nanoseconds each frame should take for our target assets hertz
    final double timeBetweenUpdates = 1000000000 / gameSpeed;

    //At most, we'll update the assets this many times before a new render
    final int maxUpdatesBeforeRender = 1;

    //If we're able to get as high as the given FPS, don't render again
    final double targetFps = 5;
    final double targetTimeBetweenRenders = 1000000000 / targetFps;

    private int frameCount = 0;
    private int gameFps = 0;

    @Override
    public void run() {

        //We'll need the last update and render time
        double lastUpdateTime = System.nanoTime();
        double lastRenderTime = System.nanoTime();

        //Get the FPS
        int lastSecondTime = (int) (lastUpdateTime / 1000000000);

        //Only calculate assets changes if the assets is running
        while (Board.inGame) {

            double now = System.nanoTime();
            int updateCount = 0;

            //Do as many assets updates required, if the system needs to play catchup
            while (now - lastUpdateTime > timeBetweenUpdates && updateCount < maxUpdatesBeforeRender) {

                //TODO: ADD UPDATE GAME METHOD
                //Game.runNextGameFrame();
                lastUpdateTime += timeBetweenUpdates;
                updateCount++;
            }

            //Allow the system to drop frames so it doesn't need to do a ton of catchup for some reason
            if (now - lastUpdateTime > timeBetweenUpdates) {

                lastUpdateTime = now - timeBetweenUpdates;
            }


            int thisSecond = (int) (lastUpdateTime / 1000000000);
            if (thisSecond > lastSecondTime) {

                //TODO: Update the assets frame here

                System.out.println("NEW SECOND " + thisSecond + " " + frameCount);
                gameFps = frameCount;
                frameCount = 0;
                lastSecondTime = thisSecond;

            }

            //Yield until it's been at least the target time between renders. Save some CPU power in the process
            while (now - lastRenderTime < targetTimeBetweenRenders && now - lastUpdateTime < timeBetweenUpdates) {

                Thread.yield();

                //Save some CPU power
                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                }

                now = System.nanoTime();
            }

        }
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            JFrame game = new Core();
            game.setVisible(true);
        });
    }
}
