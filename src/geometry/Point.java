package geometry;
/**
 * DESCRIPTION AND CREATOR:
 * The following class includes the object 'point' (x,y), with the following fields:
 * (double) x coordinate.
 * (double) y coordinate.
 * (double) distance to the collusion object, if any.
 * operations:
 * basic set,get,toString methods.
 * comparing to other points.
 * regenerating the point randomly.
 * calculating the distance to other points.
 * @author Roei Cohen.
 * ID 325714152.
 */
public class Point {

    //private x, y coordinates
    private double x;
    private double y;

    /**
     * constructor.
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /** @return the x value of this point */
    public double getX() {
        return this.x;
    }

    /** @return the y value of this point */
    public double getY() {
        return this.y;
    }

    /**
     * changes the x coordinate.
     * @param val the new value
     */
    public void setX(double val) {
        this.x = val;
    }

    /**
     * changes the y coordinate.
     * @param val the new value
     */
    public void setY(double val) {
        this.y = val;
    }

    /**
     * @param other other point
     * @return the distance of this point to the other point
     */
    public double distance(Point other) {
        return this.distance(other.x, other.y);
    }

    /**
     * Calculates the distance between this point to (valX,valY) point.
     * @param valX x coordinate
     * @param valY y coordinate
     * @return the distance of this point to the other point
     */
    public double distance(double valX, double valY) {
        return Math.sqrt(Math.pow(this.x - valX, 2) + Math.pow(this.y - valY, 2));
    }

    /**
     * @param other the point we are comparing to.
     * @return equals -- return true is the points are equal, false otherwise
     */
    public boolean isEquals(Point other) {
        return this.isEquals(other.x, other.y);
    }

    /**
     * @param valX the x value
     * @param valY the y value
     * @return true iff the x,y values are correct
     */
    public boolean isEquals(double valX, double valY) {
        return (Math.abs(this.x - valX) < Math.pow(10, -8)) && (Math.abs(this.y - valY) < Math.pow(10, -8));
    }

    /**
     * Determines if this point is on a given array of lines.
     * @param lines the given lines
     * @return a boolean value, true if the point is in AT LEAST ONE of the lines, false if not
     */
    public boolean inList(java.util.List<Line> lines) {
        for (Line l : lines) {
            if (l.includes(this)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Overrides the printing format from object.java.
     * @return the string in the following "(x,y)" format
     */
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}
