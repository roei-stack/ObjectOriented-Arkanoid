package sprites;
import animations.GameLevel;
import biuoop.DrawSurface;

/**
 * @author Roei Cohen
 * ID 325714152
 */

public interface Sprite {

    /**
     * draw the sprite to the screen.
     * @param d the draw surface
     */
    void drawOn(DrawSurface d);

    /** notify the sprite that time has passed. */
    void timePassed();

    /** @param gameLevel adds the sprite to the gameLevel. */
    void addToGame(GameLevel gameLevel);
}
