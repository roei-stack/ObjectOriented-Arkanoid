package tests;
import collusion.HitListener;
import sprites.Ball;
import sprites.Block;

public class PrintingHitListener implements HitListener {
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("hit");
    }
}
