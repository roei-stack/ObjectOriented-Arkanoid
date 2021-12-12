package backgrounds;
import animations.AnimationRunner;
import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import sprites.Ball;
import sprites.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Roei Cohen
 * ID 325714152
 */
public class DotsBackGround extends BackgroundDecorator {

    // i may change this to a sprite collection and the dots moving...
    private final List<Ball> balls = new ArrayList<>();

    /**
     * spreads dots around the screen. equally, and randomly.
     * @param dotsDensity the density of the dots.
     * number of dots == density * density
     * @param color the dot's color
     * @param background the background being decorated
     */
    public DotsBackGround(Color color, int dotsDensity, Sprite background) {
        super(background);
        // spreading dots equally in the screen
        for (int i = 0; i < dotsDensity; i++) {
            for (int j = 0; j < dotsDensity; j++) {
                Rectangle rct = new Rectangle(
                        new Point((j * AnimationRunner.WIDTH) / dotsDensity,
                                (i * AnimationRunner.HEIGHT) / dotsDensity),
                        AnimationRunner.WIDTH / dotsDensity, AnimationRunner.HEIGHT / dotsDensity);
                Ball b = new Ball(rct.generatePointInside(), 3, color);
                balls.add(b);
            }
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        super.drawOn(d);
        for (Ball b : balls) {
            b.drawOn(d);
        }
    }
}
