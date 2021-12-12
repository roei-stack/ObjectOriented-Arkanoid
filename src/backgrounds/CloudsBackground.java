package backgrounds;

import biuoop.DrawSurface;
import sprites.Sprite;

import java.awt.Color;

/**
 * @author Roei Cohen
 * ID 325714152
 */
public class CloudsBackground extends BackgroundDecorator {

    /**@param background the background being decorated. */
    public CloudsBackground(Sprite background) {
        super(background);
    }

    @Override
    public void drawOn(DrawSurface d) {
        super.drawOn(d);
        d.setColor(Color.WHITE);
        for (int i = 0; i < 10; i++) {
            d.drawLine(250 + (10 * i), 300, 80 + (i * 10), 600);
        }
        d.setColor(new Color(177, 177, 177));
        d.fillCircle(250, 300, 25);
        d.fillCircle(270, 320, 29);
        d.setColor(new Color(163, 163, 163));
        d.fillCircle(290, 290, 31);
        d.setColor(new Color(147, 147, 147));
        d.fillCircle(310, 320, 25);
        d.fillCircle(330, 300, 34);
        // second cloud
        d.setColor(Color.WHITE);
        for (int i = 0; i < 10; i++) {
            d.drawLine(650 + (i * 10), 380, 600 + (i * 10), 600);
        }
        d.setColor(new Color(177, 177, 177));
        d.fillCircle(650, 350, 23);
        d.fillCircle(670, 390, 27);
        d.setColor(new Color(163, 163, 163));
        d.fillCircle(690, 360, 29);
        d.setColor(new Color(147, 147, 147));
        d.fillCircle(700, 380, 22);
        d.fillCircle(720, 370, 32);
    }
}
