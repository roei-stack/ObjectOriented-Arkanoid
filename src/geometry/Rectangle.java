package geometry;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Roei Cohen
 * ID 325714152
 * The following class defines a "rectangle" object, with the following fields:
 * (Point) the upper left point of the rectangle
 * (double) the width and height of the rectangle
 * It allows the following operations:
 * basic set, get and construct methods
 * get an intersection list of points between this rectangle and a given line
 */

public class Rectangle {

    // Private fields
    private final Point upperLeft;
    private final double width;
    private final double height;

    /**
     * Constructs a new rectangle with location and width/height.
     * @param upperLeft the upper left corner of the rectangle
     * @param width the rectangle's width (aka the horizontal dimension)
     * @param height the rectangle's height (aka the vertical dimension)
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /** @return all 4 points of the rectangle, in 1 array */
    public Point[] getPoints() {
        // The points are calculated based on the upside down y-axis in the biuoop gui package
        Point p2 = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY()); // The upper right
        Point p3 = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height); //↓-right
        Point p4 = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height); // Bottom left
        return new Point[]{this.upperLeft, p2, p3, p4};
    }

    /**
     * Determines if the point p is inside this rectangle.
     * @param p the given point
     * @return a boolean value:
     * true => the point is inside
     * false => not inside
     */
    public boolean includes(Point p) {
        return p.getY() > this.upperLeft.getY() && p.getY() < this.upperLeft.getY() + this.height
                && p.getX() > this.upperLeft.getX() && p.getX() < this.upperLeft.getX() + this.width;
    }

    /** @return all 4 line segments of the rectangle, in 1 array */
    public java.util.List<Line> getLines() {

        java.util.List<Line> lines = new ArrayList<>();

        Point[] points = this.getPoints();
        // Therefore, lines[0] and lines[2] are the HORIZONTAL lines, and lines[1/3] are VERTICAL!
        lines.add(new Line(points[0], points[1]));
        lines.add(new Line(points[1], points[2]));
        lines.add(new Line(points[2], points[3]));
        lines.add(new Line(points[3], points[0]));

        return lines;
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     * @param line the given line
     * @return a list of intersection points between the line and the rectangle
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> intersections = new ArrayList<>();
        // checking if there is intersection with the corners of the rectangle
        Point[] corners = this.getPoints();
        java.util.List<Line> lines = this.getLines();
        for (Point p : corners) {
            if (line.includes(p)) {
                intersections.add(p);
            }
        }
        // for each line in the rectangle, check if there is an intersection
        for (Line l : lines) {
            Point intersection = line.intersectionWith(l);
            // we will add a new intersection to the list only if it's a new intersection so we wont have it twice
            if (intersection != null && !intersections.contains(intersection)) {
                intersections.add(intersection);
            }
        }
        // In a special case, the line would intersect 2 edges in one point, so we account for it
        if (intersections.size() == 2 && intersections.get(0).equals(intersections.get(1))) {
            intersections.remove(1);
        }
        return intersections;
    }

    /** @return the width of the rectangle */
    public double getWidth() {
        return this.width;
    }

    /** @return the height of the rectangle */
    public double getHeight() {
        return this.height;
    }

    /** @return the upper-left point of the rectangle */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * @return a generated point inside of the rectangle. used to make beautiful background effects.
     */
    public Point generatePointInside() {
        Random rand = new Random();
        int dotRad = 3;
        int x = (int) (dotRad - 1 + this.upperLeft.getX() + rand.nextInt((int) this.width - dotRad));
        int y = (int) (dotRad - 1 + this.upperLeft.getY() + rand.nextInt((int) this.height - dotRad));
        return new Point(x, y);
    }

    /**@return a string for debugging purposes*/
    public String toString() {
        return "upper left: " + this.upperLeft + ", Width: " + this.width + ", Height: " + this.height;
    }
}
