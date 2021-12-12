package levels;
import animations.AnimationRunner;
import backgrounds.BasicBackground;
import backgrounds.DotsBackGround;
import backgrounds.MassageBox;
import backgrounds.TargetBackground;
import collusion.Velocity;
import geometry.Point;
import geometry.Rectangle;
import sprites.Block;
import sprites.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Roei Cohen
 * ID 325714152
 * settings file for the "direct hit" level
 */
public class DirectHitLevel implements LevelInformation {

    // adjust return values to change level's behaviour, for more information go to "LevelInformation.java"
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return Velocity.spreadEqually(numberOfBalls(), 5);
    }

    @Override
    public List<Color> initialBallColors() {
        List<Color> l = new ArrayList<>();
        l.add(Color.BLUE);
        return l;
    }

    @Override
    public int paddleSpeed() {
        return 7;
    }

    @Override
    public int paddleWidth() {
        return 85;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new MassageBox(blocks().get(0).getCollisionRectangle().getUpperLeft(), "Im in danger!",
                new TargetBackground(new Point(AnimationRunner.WIDTH / 2, (AnimationRunner.HEIGHT / 4) + 30),
                        Color.GREEN, new DotsBackGround(Color.WHITE, 10, new BasicBackground(Color.BLACK))));
    }

    @Override
    public List<Block> blocks() {
        List<Block> a = new ArrayList<>();
        a.add(new Block(new Rectangle(new Point((AnimationRunner.WIDTH / 2) - 30, AnimationRunner.HEIGHT / 4),
                60, 60), Color.RED));
        return a;
    }

    // default = 1
    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
