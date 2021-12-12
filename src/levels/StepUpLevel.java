package levels;
import animations.AnimationRunner;
import animations.GameLevel;
import backgrounds.BasicBackground;
import backgrounds.BuildingBackground;
import backgrounds.DotsBackGround;
import backgrounds.MassageBox;
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
 * settings file for the third level
 */
public class StepUpLevel implements LevelInformation {

    // adjust return values to change level's behaviour, for more information go to "LevelInformation.java"
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return Velocity.spreadEqually(this.numberOfBalls(), 6);
    }

    @Override
    public List<Color> initialBallColors() {
        List<Color> list = new ArrayList<>();
        list.add(Color.BLACK);
        list.add(Color.GREEN);
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 90;
    }

    @Override
    public String levelName() {
        return "Step Up";
    }

    @Override
    public Sprite getBackground() {
        //todo create background class implementing sprite
        return new MassageBox(new Point(115, 450), "buildings win!",
                new DotsBackGround(Color.BLUE, 8, new BuildingBackground(new BasicBackground(Color.BLUE))));
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        int w = AnimationRunner.WIDTH / 16, h = AnimationRunner.HEIGHT / 26;
        java.awt.Color[] colors = {Color.LIGHT_GRAY, Color.GREEN, Color.YELLOW, Color.RED, Color.CYAN, Color.MAGENTA};
        for (int row = 0; row < 5; row++) {
            for (int i = 1; i <= 11 - row; i++) {
                // row * 3 and i * 3 let us have 3 pixels of space between each rectangle giving an elegant look
                Rectangle rct =
                        new Rectangle(new Point(AnimationRunner.WIDTH - i * w - GameLevel.WALLS_THICKNESS - 3 * i,
                                (AnimationRunner.HEIGHT / 7) + row * h + row * 3), w, h);
                blocks.add(new Block(rct, colors[row]));
            }
        }
        return blocks;
    }

    // default = 45
    @Override
    public int numberOfBlocksToRemove() {
        return 45;
    }
}
