package collusion;
import sprites.Ball;
import sprites.Block;

/**
 * The hit listener interface allows us to implement any kind of listener who can determine.
 * what will happen after a ball collides with a block
 * @author Roei Cohen
 * ID 325714152
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit the block
     * @param hitter the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
