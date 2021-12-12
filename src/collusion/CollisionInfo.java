package collusion;
import geometry.Point;

/**
 * DESCRIPTION AND CREATOR...
 * the collusion info object is created when a collusion is occurred
 * its used to pass the collusion point and collusion object from a collidable in the environment.
 * it has no other purpose other then keeping this project organized
 * @author Roei Cohen
 * ID 325714152
 */
public class CollisionInfo {

    private final Point collusionPoint;
    private final Collidable collusionObject;

    /**
     * Basic constructor.
     * @param collusionPoint the point of collusion
     * @param collusionObject the object we are colliding with
     */
    public CollisionInfo(Point collusionPoint, Collidable collusionObject) {
        this.collusionPoint = collusionPoint;
        this.collusionObject = collusionObject;
    }

    /** @return the point at which the collision occurs */
    public Point collisionPoint() {
        return this.collusionPoint;
    }

    /** @return the collidable object involved in the collision */
    public Collidable collisionObject() {
        return this.collusionObject;
    }
}
