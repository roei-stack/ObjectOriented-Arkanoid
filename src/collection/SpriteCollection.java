package collection;
import biuoop.DrawSurface;
import sprites.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * DESCRIPTION AND CREATOR...
 * sprite collection object stores the sprites in our game in one list so it's easier to work with
 * we can add a sprite to our list, draw the sprites on a given draw surface, and notify them that time passed
 * @author Roei Cohen
 * ID 325714152
 */

public class SpriteCollection {

    private final java.util.List<Sprite> sprites = new ArrayList<>();

    /** @param s a new sprite being added */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**@return the collection of sprites*/
    public java.util.List<Sprite> getSprites() {
        return this.sprites;
    }

    /** call timePassed() on all sprites. */
    public void notifyAllTimePassed() {
        List<Sprite> spriteCollections = new ArrayList<>(this.sprites);
        for (Sprite s : spriteCollections) {
            s.timePassed();
        }
    }

    /**
     * call drawOn() on all sprites.
     * @param d the draw surface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : this.sprites) {
            s.drawOn(d);
        }
    }
}
