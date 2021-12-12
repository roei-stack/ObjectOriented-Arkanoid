package animations;
import biuoop.DrawSurface;
import collection.SpriteCollection;
import java.awt.Color;

/**
 * @author Roei Cohen
 * ID 325714152
 */
public class PauseScreen implements Animation {

    private final SpriteCollection sprites;

    /**
     * Constructor - Receives reference to the keyboard.
     * @param s the sprites
     */
    public PauseScreen(SpriteCollection s) {
        this.sprites = s;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        sprites.drawAllOn(d);
        d.setColor(new Color(177, 84, 220));
        d.drawText(170, 65, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
