package animations;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Roei Cohen
 * ID 325714152
 * this class is used to decorate animations,
 * it runs a specific animation with a keyboard reference
 * until a specific key is pressed
 */
public class KeyPressStoppableAnimation implements Animation {

    private final Animation animation;
    private final KeyboardSensor keyboard;
    private final String key;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Constructor. takes a sensor, and runs a given animation until the key is pressed.
     * @param sensor the keyboard so we can read from it
     * @param key we will stop the animation once this key is pressed
     * @param animation the animation will be run until the key is pressed
     * param animation this animation will be run
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.keyboard = sensor;
        this.key = key;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        boolean pressed = this.keyboard.isPressed(this.key);
        if (!pressed) {
            this.isAlreadyPressed = false;
        } else if (!this.isAlreadyPressed) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
