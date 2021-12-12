package collection;
import geometry.Point;
import geometry.Line;
import collusion.Collidable;
import collusion.CollisionInfo;

import java.util.ArrayList;
/**
 * DESCRIPTION AND CREATOR...
 * The game environment is a collection of collidables for our game
 * we can add a collidable, and get the closest collusion for a given line
 * @author Roei Cohen
 * ID 325714152.
 */

public class GameEnvironment {

    private final java.util.List<Collidable> collidables = new ArrayList<>();

    /** @param c the new collidable object */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /** @return the list of collidable objects */
    public java.util.List<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * Assume an object moving from line.start() to line.end(),
     * search for the closest collusion and return it's details.
     * @param trajectory the object's movement line
     * @return (CollusionInfo) the collusion information. if there is no collusion, return null
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        short len = (short) this.collidables.size();
        for (short i = 0; i < len; i++) {
            Collidable currObj = this.collidables.get(i);
            Point intersection = trajectory.closestIntersectionToStartOfLine(currObj.getCollisionRectangle());
            if (intersection != null) {
                return new CollisionInfo(intersection, currObj);
            }
        }
        return null;
    }
}

