package animations;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

/**
 * @author Roei Cohen
 * ID 325714152
 */
public class AnimationRunner {

    // editable constants, recommended value is shown on the right
    public static final int WIDTH = 800; //800
    public static final int HEIGHT = 600; //600
    private final int fps; //60
    // gui for displaying and sleeper for making the animation
    private final GUI gui = new GUI("Arkanoid", WIDTH, HEIGHT);
    private final Sleeper sleeper = new Sleeper();

    /**
     * Constructor. initializes a runner with a given fps value
     * @param framesPerSecond the fps of the animation
     */
    public AnimationRunner(int framesPerSecond) {
        this.fps = framesPerSecond;
    }

    /**
     * run - start the animation loop.
     * @param animation the given animation
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / fps;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**@return a reference to the keyboard. */
    KeyboardSensor keyboard() {
        return gui.getKeyboardSensor();
    }

    /**kills the gui before closing program. */
    void kill() {
        gui.close();
    }
}
