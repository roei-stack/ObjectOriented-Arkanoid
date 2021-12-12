package collusion;
import animations.GameLevel;
import geometry.Counter;
import sprites.Ball;
import sprites.Block;

/**
 * @author Roei Cohen
 *  * ID 325714152
 * This class manages the hitListener whose job is to introduce a new ball to the gameLevel upon collusion
 * It consists of 2 fields
 * -a reference to the GameLevel object
 * -a counter keeping track of the remaining balls
 */
public class BallHealer implements HitListener {

    // private references
    private final GameLevel gameLevel;
    private final Counter remainingBalls;

    /**
     * Constructor.
     * @param gameLevel reference to the gameLevel
     * @param remainingBalls counter
     */
    public BallHealer(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }

    /**
     * introduces a new ball upon collusion.
     * @param beingHit the block whose getting hit
     * @param hitter the hitting ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        Ball.createBallsOnPaddle(gameLevel, 1);
        this.remainingBalls.increase(1);
    }
}
