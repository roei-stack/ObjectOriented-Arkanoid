package sprites;
import collusion.HitListener;
import geometry.Counter;

/**
 * @author Roei Cohen
 * ID 325714152
 * this listener is used to track scores whenever there is a hit
 */
public class ScoreTrackingListener implements HitListener {

    private final Counter currentScore;

    /**@param score the default score. */
    public ScoreTrackingListener(Counter score) {
        this.currentScore = score;
    }

    /**
     * Adds 5 points when a ball hits the referenced block.
     * @param beingHit the block
     * @param hitter the Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}
