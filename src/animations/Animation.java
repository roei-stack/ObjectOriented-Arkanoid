package animations;

import biuoop.DrawSurface;

/**
 * @author Roei Cohen
 * ID 325714152
 * The animation interface
 */
public interface Animation {

    /**
     * Draws a picture representing the current frame on a given draw surface.
     * @param d the draw surface
     */
    void doOneFrame(DrawSurface d);

    /**@return true if the animation should be stopped. */
    boolean shouldStop();
}
