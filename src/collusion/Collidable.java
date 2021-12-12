package collusion;
import geometry.Point;
import geometry.Rectangle;
import sprites.Ball;

/**
 * @author Roei Cohen
 * ID 325714152.
 */
public interface Collidable {
    /** @return the "collision shape" of the object.*/
    Rectangle getCollisionRectangle();

    /**
     * "Notify" the object that we collided with it at collisionPoint with a given velocity.
     * @param hitter the hitting ball
     * @param collisionPoint the collusion point
     * @param currentVelocity the velocity
     * @return the new velocity expected after the hit (based on the force the object inflicted on us)
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
