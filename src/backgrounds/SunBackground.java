package backgrounds;

import biuoop.DrawSurface;
import geometry.Point;
import sprites.Sprite;
import java.awt.Color;

/**
 * @author Roei Cohen
 * ID 325714152
 */
public class SunBackground extends BackgroundDecorator {

    // the height the run rays reach
    private final int dest;
    private final int startx;
    private final int endx;
    private final Point sunCenter;

    /**
     * @param sunCenter this is where we draw the sun's circle
     * @param sunRaysDestination this is where the sun rays will hit (y = this thing)
     * @param start the range of the axis the rays will hit
     * @param end the range of the axis the rays will hit
     * @param background the background being decorated.
     */
    public SunBackground(Point sunCenter, int sunRaysDestination, int start, int end, Sprite background) {
        super(background);
        this.dest = sunRaysDestination;
        this.sunCenter = sunCenter;
        this.startx = start;
        this.endx = end;
    }

    @Override
    public void drawOn(DrawSurface d) {
        super.drawOn(d);
        d.setColor(Color.YELLOW);
        for (int i = startx; i <= endx; i += 15) {
            d.drawLine((int) this.sunCenter.getX(), (int) this.sunCenter.getY(), i, this.dest);
        }
        d.setColor(Color.YELLOW.darker());
        d.fillCircle((int) this.sunCenter.getX(), (int) this.sunCenter.getY(), 55);

        d.setColor(Color.YELLOW);
        d.fillCircle((int) this.sunCenter.getX(), (int) this.sunCenter.getY(), 45);
    }
}
