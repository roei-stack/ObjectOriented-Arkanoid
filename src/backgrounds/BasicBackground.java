package backgrounds;
import animations.AnimationRunner;
import animations.GameLevel;
import biuoop.DrawSurface;
import sprites.Sprite;
import java.awt.Color;

/**
 * @author Roei Cohen
 * ID 325714152
 */
public class BasicBackground implements Sprite {
    // a simple, plain color background
    private final Color c;

    /**@param color the background color. */
    public BasicBackground(Color color) {
        this.c = color;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.c);
        d.fillRectangle(0, 0, AnimationRunner.WIDTH, AnimationRunner.HEIGHT);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
