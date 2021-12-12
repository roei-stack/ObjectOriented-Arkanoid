package geometry;
/**
 * DESCRIPTION AND CREATOR:
 * The following class includes the object 'line' that represents a line segment in 2d vector space.
 * It includes the following fields:
 * (Point) start.
 * (Point) end.
 * Operations:
 * basic set/get/construct and toString methods.
 * comparing 2 line segments.
 * calculating the length of a segment.
 * calculating the middle point of the line segment.
 * determine if a certain point is in a line.
 * determine if the object line collides with another line.
 * Finding the collusion point between 2 line segments.
 * Returning the closest intersection point between the start of this line to a given rectangle
 * @author Roei Cohen.
 * ID 325714152.
 */
public class Line {

    //private start, end points fields
    private Point start;
    private Point end;

    /**
     * Constructors, defines the segment line by 2 points.
     * @param start the start point
     * @param end the end point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs the line by 4 numbers => start = (x1,y1), end = (x2,y2).
     * @param x1 the x coordinate of the first segment line
     * @param y1 the y coordinate of the first segment line
     * @param x2 the x coordinate of the second segment line
     * @param y2 the y coordinate of the second segment line
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /** @return the start point of the line */
    public Point start() {
        return this.start;
    }

    /** @return the end point of the line */
    public Point end() {
        return this.end;
    }

    /**
     * Calculates the length of the segment.
     * @return the length of the line
     */
    public double length() {
        return this.start.distance(end);
    }

    /**
     * Calculates the middle point of a segment, by the formula => ((x1+x2)/2,(y1+y2)/2).
     * @return the middle point of the line segment
     */
    public Point middle() {
        return new Point((this.start.getX() + this.end.getX()) / 2, (this.start.getY() + this.end.getY()) / 2);
    }

    /**
     * checks if 2 lines are identical.
     * @param other the line were compering to
     * @return a boolean value
     */
    public boolean isEquals(Line other) {
        return (start.isEquals(other.start) && end.isEquals(other.end))
                || (end.isEquals(other.start) && start.isEquals(other.end));
    }

    /**
     * Calculates the orientation of a certain point p, with respect to 'this' line.
     * @param p the point
     * @return 0,1,-1
     * 0 => the point is collinear to the line
     * 1 => clockwise orientation
     * -1 => CounterClockwise
     */
    private short orientation(Point p) {

        Point v1End = new Point(p.getX() - this.end.getX(), p.getY() - this.end.getY());
        Point v2Start = new Point(p.getX() - this.start.getX(), p.getY() - this.start.getY());
        double crossProduct = (v1End.getX() * v2Start.getY()) - (v1End.getY() * v2Start.getX());
        if (crossProduct > 0) {
            // Positive product => clockwise
            return 1;
        } else if (crossProduct < 0) {
            // Negative Product => counter clockwise
            return -1;
        }
        // 0, aka no direction (because Product is |u|*|v|*alpha => alpha == 0, excluding special, see line 155)
        return 0;
    }

    /**
     * Determines if a given point is on a line segment.
     * @param p the given point
     * @return a boolean value
     * true => 'p' is on 'this' line
     * false => 'p' is not on 'this' line
     */
    public boolean includes(Point p) {
        double d1 = p.distance(this.start);
        double d2 = p.distance(this.end);
        double d3 = this.length();
        // Return d3 == d1 + d2, to avoid double precision errors
        return Math.abs(d3 - d2 - d1) < Math.pow(10, -8);
    }

    /**
     * Determines if 2 finite line segments intersect with each other.
     * @param other the other line
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {

        // Special case - one of the lines/both lines represents a single point
        if (this.start.equals(this.end) && other.includes(this.start)) {
            return true;
        }
        if (other.start.equals(other.end) && this.includes(other.start)) {
            return true;
        }

        // Calculating the directions of each point with respect to each line
        short d1 = this.orientation(other.start());
        short d2 = this.orientation(other.end());
        short d3 = other.orientation(this.start());
        short d4 = other.orientation(this.end());

        // On 99% of intersection cases (when the lines are linear independent), this condition will occur
        if (d1 != d2 && d3 != d4) {
            return true;
        }

        // If 'other.start' is collinear with 'this' line, check if they are connecting with each other
        if (d1 == 0 && this.isConnecting(other)) {
            return true;
        }
        // Similar
        if (d2 == 0 && this.isConnecting(other)) {
            return true;
        }
        // Similar
        if (d3 == 0 && this.isConnecting(other)) {
            return true;
        }
        //java likes this better:
        return d4 == 0 && this.isConnecting(other);

        /*
         * The code above is identical to :
         * if d4 == 0 and this is Connecting with other => return true
         * otherwise, return false
         */
    }

    /**
     * Receives a collinear line to this line, and determines if they connect only once.
     * @param line the other line
     * @return boolean value
     * Checks if the collinear lines connect and don't overlap each other by comparing the end and start points
     * of each segment
     */
    private Point isConnectOnce(Line line) {

        // Default values
        Point max1 = this.start;
        Point min1 = this.end;
        Point max2 = this.start;
        Point min2 = this.end;

        if (this.end.getX() > this.start.getX()) {
            max1 = this.end;
            min1 = this.start;
        } else if (this.start.getX() > this.end.getX()) {
            max1 = this.start;
            min1 = this.end;
        } else {
            if (this.start.getY() > this.end.getY()) {
                max1 = this.start;
                min1 = this.end;
            } else if (this.start.getY() < this.end.getY()) {
                max1 = this.end;
                min1 = this.start;
            }
        }
        if (line.end.getX() > line.start.getX()) {
            max2 = line.end;
            min2 = line.start;
        } else if (line.start.getX() > line.end.getX()) {
            max2 = line.start;
            min2 = line.end;
        } else {
            if (line.start.getY() > line.end.getY()) {
                max2 = line.start;
                min2 = line.end;
            } else if (line.start.getY() < line.end.getY()) {
                max1 = line.end;
                min1 = line.start;
            }
        }
        // Main check
        if (max1.equals(min2)) {
            return max1;
        }
        if (min1.equals(max2)) {
            return min1;
        }
        return null;
    }

    /**
     * Checks if the 2 collinear line segments are intersecting.
     * @param line a line whose collinear to this line
     * @return true => the line segments are intersecting, false => they are not intersecting
     */
    private boolean isConnecting(Line line) {
        return line.start.distance(this.start) + line.start.distance(this.end) == this.length()
                || line.end.distance(this.start) + line.end.distance(this.end) == this.length();
    }

    /**
     * Calculates the collusion point formed by 2 line segments.
     * @param other the other line (the segment we are colliding with)
     * @return The point of collusion, if there is no unique solution, return null
     */
    public Point intersectionWith(Line other) {

        if (!this.isIntersecting(other)) {
            return null;
        }
        // Special case: one of the lines/both lines represents a single point
        if (this.start.equals(this.end) && other.includes(this.start)) {
            return this.start;
        }
        if (other.start.equals(other.end) && this.includes(other.start)) {
            return other.start;
        }

        // Line AB is represented as a1*x + b1*y = c1, and line CD is represented as a2*x + b2*y = c2
        double a1 = this.end.getY() - this.start.getY();
        double b1 = this.start.getX() - this.end.getX();
        double c1 = a1 * (this.start.getX()) + b1 * (this.start.getY());

        double a2 = other.end.getY() - other.start.getY();
        double b2 = other.start.getX() - other.end.getX();
        double c2 = a2 * (other.start.getX()) + b2 * (other.start.getY());

        // The determinant of the equation matrix
        double det =  a1 * b2 - a2 * b1;
        if (Math.abs(det) < Math.pow(10, -8)) {
            // Null if the lines are overlapping, returns the unique collusion point otherwise
            return this.isConnectOnce(other);
        }
        // Applying 'Rol law', we can easily calculate the solution
        double x = (b2 * c1 - b1 * c2) / det;
        double y = (a1 * c2 - a2 * c1) / det;
        return new Point(x, y);
    }

    /**
     * Searches for the closest intersection point between this line and a given rectangle.
     * @param rect the given rectangle
     * @return a point
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // looking for intersection points...
        java.util.List<Point> intersections = rect.intersectionPoints(this);
        //if no intersections found, return null
        if (intersections.size() == 0) {
            return null;
        }
        // if one intersection found, by definition it's the closest one
        if (intersections.size() == 1) {
            return intersections.get(0);
        }
        //return the closest intersection to the start of the line by calculating the distances
        double d0 = intersections.get(0).distance(this.start);
        double d1 = intersections.get(1).distance(this.start);

        if (d0 < d1) {
            return intersections.get(0);
        }
        return intersections.get(1);
    }

    /**
     * Prints the line as "(x1,y1) <=> (x2,y2)".
     * Overrides the original toString method from object.java
     * @return String that represents the line
     */
    public String toString() {
        return this.start + " <=> " + this.end;
    }
}
