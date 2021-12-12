package collusion;
import geometry.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * DESCRIPTION AND CREATOR:
 * The following class defines the object 'Velocity', it's fields are:.
 * (int) dx => the change of a point on the x-axis
 * (int) dy => the change of a point on the y-axis
 * Possible operations:
 * Basic set, get, and construct methods
 * Apply this velocity to a point (x, y) => (x + dx, y + dy)
 * Receiving velocity by speed and angle and returning the actual velocity: (alpha, speed) => (speedX, speedY)
 * return the absolute speed from this velocity
 * Generate a random velocity v, with speed from 50-70, and with a random angle
 * @author Roei Cohen.
 * ID 325714152
 */
public class Velocity {

    // Private fields
    private double dx;
    private double dy;

    /**
     * Constructor.
     * @param dx the change of a point on the x-axis
     * @param dy the change of a point on the y-axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /** @return the change on the x-axis */
    public double getDx() {
        return this.dx;
    }

    /** @return the change on the y-axis */
    public double getDy() {
        return this.dy;
    }

    /**
     * Changes the velocity on the x-axis.
     * @param newVal the new value
     */
    public void setDx(double newVal) {
        this.dx = newVal;
    }

    /**
     * Changes the velocity on the y-axis.
     * @param newVal the new value
     */
    public void setDy(double newVal) {
        this.dy = newVal;
    }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     * @param p the given point
     * @return (x,y) => (x + dx, y + dy)
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * Receives velocity from angle and speed, and returns it by (dx,dy) format.
     * @param angle the angle (in non radians)
     * @param speed the absolute speed => |v|
     * @return The velocity by (dx, dy)
     * dx = speed*sin(a), dy = speed*cos(a), when a = alpha in radians
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double angleRad = angle * (Math.PI / 180);
        return new Velocity(speed * Math.sin(angleRad), -1 * speed * Math.cos(angleRad));
    }

    /**
     * generate x velocities pointing up with equal angles between each other (such as 45, 90, 135).
     * @param amount the amount of velocities
     * @param speed the speed of each generated velocity
     * @return a new list with all the velocities
     */
    public static List<Velocity> spreadEqually(int amount, int speed) {
        List<Velocity> a = new ArrayList<>();
        for (int i = 1; i <= amount; i++) {
            // spreads the balls with equal angles between each other
            int angle = (i * (180 / (amount + 1))) - 90;
            a.add(Velocity.fromAngleAndSpeed(angle, speed));
        }
        return a;
    }

    /** @return the absolute speed*/
    public double absoluteSpeed() {
        return Math.sqrt(Math.pow(this.dx, 2) + Math.pow(this.dy, 2));
    }
}
