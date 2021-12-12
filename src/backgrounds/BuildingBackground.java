package backgrounds;
import biuoop.DrawSurface;
import sprites.Sprite;
import java.awt.Color;

/**
 * @author Roei Cohen
 * ID 325714152
 */
public class BuildingBackground extends BackgroundDecorator {

    /**@param background the background being decorated. */
    public BuildingBackground(Sprite background) {
        super(background);
    }

    @Override
    public void drawOn(DrawSurface d) {
        int x = 125;
        int y = 455;
        super.drawOn(d);
        d.setColor(Color.lightGray);
        d.fillRectangle(160, 250, 10, 200);

        // the top circles
        d.setColor(Color.lightGray);
        d.fillCircle(165, 250, 15);
        d.setColor(Color.GRAY);
        d.fillCircle(165, 250, 10);
        d.setColor(Color.WHITE);
        d.fillCircle(165, 250, 5);

        d.setColor(Color.lightGray);
        d.fillRectangle(150, 400, 30, 50);

        d.setColor(Color.lightGray);
        d.fillRectangle(115, 450, 100, 200);

        d.setColor(Color.WHITE);
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                d.fillRectangle(x + col * 18, y + row * 32, 10, 25);
            }
        }

    }
}
