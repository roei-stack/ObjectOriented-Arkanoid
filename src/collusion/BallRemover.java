package collusion;
import animations.GameLevel;
import geometry.Counter;
import sprites.Ball;
import sprites.Block;

/**
 * @author Roei Cohen
 * ID 325714152
 * this hit listener removes the ball that collided with the block
 */
public class BallRemover implements HitListener {

    private final GameLevel gameLevel;
    private final Counter remainingBalls;

    /**
     * constructor.
     * @param gameLevel the gameLevel
     * @param remainingBalls the remaining balls
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }

    /**
     * removes the hitter ball.
     * @param beingHit the block
     * @param hitter the Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(gameLevel);
        remainingBalls.decrease(1);
    }
}
