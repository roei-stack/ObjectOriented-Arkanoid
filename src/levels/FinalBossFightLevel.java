package levels;
import animations.AnimationRunner;
import animations.GameLevel;
import backgrounds.BasicBackground;
import backgrounds.BuildingBackground;
import backgrounds.CloudsBackground;
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
 * settings file for the "direct hit" level
 */
public class FinalBossFightLevel implements LevelInformation {

    // adjust return values to change level's behaviour, for more information go to "LevelInformation.java"
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return Velocity.spreadEqually(numberOfBalls(), 6);
    }

    @Override
    public List<Color> initialBallColors() {
        List<Color> list = new ArrayList<>();
        list.add(Color.WHITE);
        list.add(Color.BLUE);
        list.add(Color.BLACK);
        return list;

    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 80;
    }

    @Override
    public String levelName() {
        return "Final Boss Fight";
    }

    @Override
    public Sprite getBackground() {
        //todo create background class implementing sprite
        return new MassageBox(new Point(250, 300), "MUHAHAHA!",
                new CloudsBackground(new BuildingBackground(new BasicBackground(new Color(16, 228, 234)))));
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        int w = AnimationRunner.WIDTH / 16, h = AnimationRunner.HEIGHT / 26;
        java.awt.Color[] colors = {Color.DARK_GRAY, Color.LIGHT_GRAY, Color.RED,
                Color.YELLOW, Color.ORANGE, Color.MAGENTA, Color.CYAN};
        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 15; col++) {
                Rectangle rct =
                        new Rectangle(new Point(5 + GameLevel.WALLS_THICKNESS + col * w,
                                (AnimationRunner.HEIGHT / 8) + row * h), w, h);
                blocks.add(new Block(rct, colors[row]));
            }
        }
        return blocks;
    }

    // default = 105
    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }
}
