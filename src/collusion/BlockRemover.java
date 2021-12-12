package collusion;
import animations.GameLevel;
import geometry.Counter;
import sprites.Ball;
import sprites.Block;

/**
 * A BlockRemover is in charge of removing blocks from the gameLevel, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {

    private final GameLevel gameLevel;
    private final Counter remainingBlocks;

    /**
     * constructor.
     * @param gameLevel the gameLevel
     * @param removedBlocks the amount of blocks left
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Blocks that are hit should be removed from the gameLevel.
     * @param beingHit the block whose hit by the ball
     * @param hitter the hitting ball object
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(gameLevel);
        beingHit.removeHitListener(this);
        hitter.removeFromGameEnvironment(beingHit);
        remainingBlocks.decrease(1);
    }
}