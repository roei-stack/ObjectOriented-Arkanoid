package backgrounds;
import biuoop.DrawSurface;
import geometry.Point;
import sprites.Ball;
import sprites.Sprite;
import java.awt.Color;

/**
 * @author Roei Cohen
 * ID 325714152
 */
public class MassageBox extends BackgroundDecorator {

    private final String massage;
    private final Point location;

    /**
     * @param location the location of the bubble text on the gui
     * @param text the text that we will draw
     * @param background the background being decorated
     */
    public MassageBox(Point location, String text, Sprite background) {
        super(background);
        this.massage = text;
        this.location = location;
    }

    /** @param d the draw surface */
    private void drawTextBox(DrawSurface d) {
        int x = (int) this.location.getX(), y = (int) this.location.getY();
        Color c = Color.WHITE;

        Ball b1 = new Ball(location, 12, c);
        Ball b2 = new Ball(new Point(x - 18, y - 8), 15, c);
        Ball b3 = new Ball(new Point(x - 42, y - 48), 40, c);

        // draws a little cute text box
        b1.drawOn(d);
        b2.drawOn(d);
        b3.drawOn(d);
        d.setColor(Color.BLUE);
        d.drawText((int) b3.getCenter().getX() - 35, (int) b3.getCenter().getY(), this.massage, 13);
    }

    @Override
    public void drawOn(DrawSurface d) {
        super.drawOn(d);
        d.setColor(Color.WHITE);
        this.drawTextBox(d);
    }
}
