package sprites;
import animations.GameLevel;
import biuoop.DrawSurface;
import collusion.Collidable;
import collusion.HitListener;
import collusion.HitNotifier;
import collusion.Velocity;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Roei Cohen
 * ID 325714152
 * The following class defines the object "Block" whose made of a rectangle
 */

public class Block implements Collidable, Sprite, HitNotifier {

    private final Rectangle shape;
    private java.awt.Color color = Color.black;
    private final java.util.List<HitListener> hitListeners = new ArrayList<>();

    /**
     * Constructs the block.
     * @param shape the rectangle's block shape
     */
    public Block(Rectangle shape) {
        this.shape = shape;
    }

    /**
     * Constructs the block, allows for a color.
     * @param shape the rectangle's block shape
     * @param color  the block's color
     */
    public Block(Rectangle shape, java.awt.Color color) {
        this.shape = shape;
        this.color = color;
    }

    /** @return the collusion shape (the rectangle) */
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }

    /**@return the color */
    public Color getColor() {
        return this.color;
    }

    /**@param c the new color */
    public void setColor(Color c) {
        this.color = c;
    }

    /**
     * draw the block.
     * @param d the draw surface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.shape.getUpperLeft().getX(), (int)  this.shape.getUpperLeft().getY(),
                (int)  this.shape.getWidth(), (int)  this.shape.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.shape.getUpperLeft().getX(), (int)  this.shape.getUpperLeft().getY(),
                (int)  this.shape.getWidth(), (int)  this.shape.getHeight());
    }

    /** Acknowledges the block time has passed, and implements the sprite interface. */
    public void timePassed() {
        //todo stuff, maybe color fading
    }

    /** @param gameLevel the gameLevel were adding this block to */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addCollidable(this);
        gameLevel.addSprite(this);
    }

    /** @param gameLevel the gameLevel were removing this block from */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
        // removing hit listeners to save space
        List<HitListener> snitches = new ArrayList<>(hitListeners);
        for (HitListener snitch : snitches) {
            removeHitListener(snitch);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * upon collusion, all hit notifiers will be called.
     * @param hitter the hitting ball
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * @param collisionPoint the given collusion point
     * @param currentVelocity the velocity
     * @param hitter the ball
     * @return (Velocity) the expected object's velocity after the hit
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // Reminder: the even indexes of this array store the horizontal lines of the rectangle
        java.util.List<Line> lines = this.shape.getLines();
        // Checking weather the intersection point is on an horizontal line or vertical line (or maybe even both)
        if (lines.get(0).includes(collisionPoint) || lines.get(2).includes(collisionPoint)) {
            // Horizontal collusion, dy is changed
            currentVelocity.setDy(-currentVelocity.getDy());
        }
        if (lines.get(1).includes(collisionPoint) || lines.get(3).includes(collisionPoint)) {
            // Vertical collusion, dx is changed
            currentVelocity.setDx(-currentVelocity.getDx());
        }
        this.notifyHit(hitter);
        return currentVelocity;
    }

    /** @return a string, mainly for debugging purposes */
    public String toString() {
        return "SHAPE: " + this.shape + ", COLOR: " + this.color;
    }
}
