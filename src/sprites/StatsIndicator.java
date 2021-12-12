package sprites;
import animations.AnimationRunner;
import animations.GameLevel;
import biuoop.DrawSurface;
import geometry.Counter;
import geometry.Point;
import geometry.Rectangle;
import java.awt.Color;

/**
 * Score indicator is our scoreboard.
 * @author Roei Cohen
 * ID 325714152
 */
public class StatsIndicator implements Sprite {

    private final Counter playerScore;
    private final String levelName;
    private final Sprite scoreBoard = new Block(new Rectangle(new Point(0, 0), AnimationRunner.WIDTH, 15),
            Color.CYAN);

    /**
     * Constructor.
     * @param score the score counter
     * @param name the current level's name
     */
    public StatsIndicator(Counter score, String name) {
        this.playerScore = score;
        this.levelName = name;
    }

    /**@param d draws the scoreboard with the score itself. */
    public void drawOn(DrawSurface d) {
        // length of a char (approximately)
        int chLen = 7;
        scoreBoard.drawOn(d);
        d.setColor(Color.black);
        d.drawText((AnimationRunner.WIDTH / 2) - 30, 14, score(), 14);
        d.drawText(AnimationRunner.WIDTH - GameLevel.WALLS_THICKNESS - name().length() * chLen, 14, name(), 14);
    }

    /**absolutely nothing. */
    public void timePassed() {
        //todo stuff
    }

    /**@param gameLevel adds the sprite to the gameLevel. */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    /**@return Prints the score. */
    public String score() {
        return "Score: " + playerScore.getValue();
    }

    /**@return Prints the level name. */
    public String name() {
        return "Level Name: " + levelName;
    }
}
