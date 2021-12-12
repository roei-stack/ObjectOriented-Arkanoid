package backgrounds;
import biuoop.DrawSurface;
import geometry.Point;
import sprites.Sprite;
import java.awt.Color;

/**
 * @author Roei Cohen
 * ID 325714152
 */
public class TargetBackground extends BackgroundDecorator {

    private final Point center;
    private final Color targetColor;

    /**
     * @param center the target will be drawn here
     * @param c the target's color
     * @param background the background being decorated
     */
    public TargetBackground(Point center, Color c, Sprite background) {
        super(background);
        this.center = center;
        this.targetColor = c;
    }

    /**
     * draws a target around the point.
     * @param d the draw surface were drawing it on
     */
    private void drawTarget(DrawSurface d) {
        int delta = 40, size = 120, x = (int) this.center.getX(), y = (int) this.center.getY();
        //lines
        d.drawLine(x - delta, y, x - delta - size, y);
        d.drawLine(x + delta, y, x + delta + size, y);
        d.drawLine(x, y - delta, x, y - delta - size);
        d.drawLine(x, y + delta, x, y + delta + size);
        // circles
        for (int r = 10; r <= 130; r += 20) {
            d.drawCircle((int) center.getX(), (int) center.getY(), r);
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        super.drawOn(d);
        d.setColor(this.targetColor);
        this.drawTarget(d);
    }
}
