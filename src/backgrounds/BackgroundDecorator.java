package backgrounds;
import animations.GameLevel;
import biuoop.DrawSurface;
import sprites.Sprite;

/**
 * @author Roei Cohen
 * ID 325714152
 */
public abstract class BackgroundDecorator implements Sprite {

    private final Sprite background;

    /**@param background the background being decorated. */
    public BackgroundDecorator(Sprite background) {
        this.background = background;
    }

    @Override
    public void drawOn(DrawSurface d) {
        this.background.drawOn(d);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
