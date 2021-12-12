package levels;
import collusion.Velocity;
import sprites.Sprite;
import sprites.Block;
import java.awt.Color;
import java.util.List;

/**
 * @author Roei Cohen
 * ID 325714152
 * The LevelInformation interface specifies the information required to fully describe a level
 */
public interface LevelInformation {

    /**@return the amount of balls, the upper bound is 180. */
    int numberOfBalls();

    /**@return The initial velocity of each ball. */
    List<Velocity> initialBallVelocities();

    /**@return list of colors well use for the balls. */
    List<Color> initialBallColors();

    /**@return the paddle (player's) speed. */
    int paddleSpeed();

    /**@return the paddle (player's) width. */
    int paddleWidth();

    /**@return the level name who will be displayed at the top of the screen. */
    String levelName();

    /**@return a sprite with the background of the level. */
    Sprite getBackground();

    /**@return The Blocks that make up this level. */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed, before the level is considered to be "cleared".
     * @return This number should be <= blocks.size()
     */
    int numberOfBlocksToRemove();
}
