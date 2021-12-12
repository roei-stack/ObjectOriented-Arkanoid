package animations;
import backgrounds.BasicBackground;
import backgrounds.BuildingBackground;
import backgrounds.CloudsBackground;
import backgrounds.DotsBackGround;
import backgrounds.MassageBox;
import backgrounds.SunBackground;
import backgrounds.TargetBackground;
import biuoop.DrawSurface;
import geometry.Point;
import sprites.Sprite;
import java.awt.Color;

/**
 * @author Roei Cohen
 * ID 325714152
 * the end screen, shows either "you win" or "you lose"
 */
public class EndScreenAnimation implements Animation {

    private final Sprite screen;
    private final int finalScore;
    private final boolean passed;

    /**
     * @param score this score would be written
     * @param passed if passed is true => display "you win!", otherwise => "game over"
     */
    public EndScreenAnimation(int score, boolean passed) {
        Point p = new Point(90, 80);
        this.screen = new DotsBackGround(new Color(155, 168, 243), 9,
                new BuildingBackground(new CloudsBackground(new MassageBox(p, "  THE END!",
                        new TargetBackground(p, new Color(255, 212, 0),
                                new SunBackground(p, AnimationRunner.HEIGHT, 0, AnimationRunner.WIDTH,
                                        new BasicBackground(new Color(46, 217, 217))))))));
        this.finalScore = score;
        this.passed = passed;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.screen.drawOn(d);
        d.setColor(new Color(45, 6, 151));
        if (passed) {
            d.drawText(190, 45, "You Win! Your score is " + this.finalScore, 32);
        } else {
            d.drawText(190, 45, "Game Over. Your score is " + this.finalScore, 32);
        }
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
