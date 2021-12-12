package levels;
import animations.AnimationRunner;
import animations.GameLevel;
import backgrounds.BasicBackground;
import backgrounds.DotsBackGround;
import backgrounds.SunBackground;
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
 * settings file for the "wide easy" level
 */
public class BallsRushLevel implements LevelInformation {

    // adjust return values to change level's behaviour, for more information go to "LevelInformation.java"
    @Override
    public int numberOfBalls() {
        return 12;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return Velocity.spreadEqually(this.numberOfBalls(), 5);
    }

    @Override
    public List<Color> initialBallColors() {
        List<Color> list = new ArrayList<>();
        list.add(Color.RED);
        list.add(Color.GREEN);
        list.add(Color.BLUE);
        list.add(Color.ORANGE);
        list.add(Color.RED);
        list.add(Color.GREEN);
        list.add(Color.BLUE);
        list.add(Color.ORANGE);
        list.add(Color.RED);
        list.add(Color.GREEN);
        list.add(Color.BLUE);
        list.add(Color.ORANGE);
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 4;
    }

    @Override
    public int paddleWidth() {
        return 550;
    }

    @Override
    public String levelName() {
        return "Balls Rush";
    }

    @Override
    public Sprite getBackground() {
        //todo create background class implementing sprite
        return new SunBackground(new Point(AnimationRunner.WIDTH / 4, AnimationRunner.HEIGHT / 5),
                AnimationRunner.HEIGHT / 3, GameLevel.WALLS_THICKNESS + 30,
                AnimationRunner.WIDTH - GameLevel.WALLS_THICKNESS - 30,
                new DotsBackGround(Color.CYAN, 7, new BasicBackground(Color.WHITE)));
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        int w = AnimationRunner.WIDTH / 16, h = AnimationRunner.HEIGHT / 26;
        java.awt.Color[] colors = {Color.LIGHT_GRAY, Color.GREEN, Color.YELLOW, Color.RED, Color.CYAN, Color.MAGENTA};
        for (int i = 1; i <= 14; i++) {
            Rectangle rct = new Rectangle(new Point(30 + ((i - 1) * w) + GameLevel.WALLS_THICKNESS,
                    AnimationRunner.HEIGHT / 3), w, h);
            if (i % 2 == 0) {
                blocks.add(new Block(rct, blocks.get(blocks.size() - 1).getColor()));
            } else {
                blocks.add(new Block(rct, colors[(i / 2) % 6]));
            }
        }
        return blocks;
    }

    // default = 14
    @Override
    public int numberOfBlocksToRemove() {
        return 14;
    }
}
