package animations;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import collection.SpriteCollection;
import java.awt.Color;

/**
 * @author Roei Cohen
 * ID 325714152
 * This animation does a 3..2..1... countdown brfore the game starts
 */
public class CountdownAnimation implements Animation {

    private final double numOfSeconds;
    private final int countFrom;
    private final SpriteCollection image;
    private int counter;
    private final Sleeper sleeper;

    /**
     * Constructor.
     * @param countFrom count from this number
     * @param numOfSeconds total count lasts this many seconds
     * @param gameScreen the list of sprites
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.counter = countFrom;
        this.image = gameScreen;
        this.sleeper = new Sleeper();
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // draw the static image
        this.image.drawAllOn(d);
        d.setColor(new Color(42, 16, 144));
        d.drawText(d.getWidth() / 2, d.getHeight() / 2, Integer.toString(counter), 32);
        // for some reason the gui sleeps this time before drawing the sprites, so i made this condition
        if (counter != countFrom) {
            // sleep for (numOfSeconds / countFrom) seconds
            sleeper.sleepFor((long) (1000 * (numOfSeconds / countFrom)));
        }
        // decrease the counter by 1
        counter--;
    }

    @Override
    public boolean shouldStop() {
        return counter < 0;
    }
}
