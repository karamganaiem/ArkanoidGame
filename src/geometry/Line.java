// Karam Ganaiem

package geometry;

import java.util.List;


/**
 * The geometry.Line class represents a line segment in a 2D plane.
 * It can be defined by two points or by four coordinates.
 * This class provides methods for calculating line properties,
 * detecting intersections, and finding intersection points.
 */
public class Line {

    private Point start;
    private Point end;
    private double x1;
    private double y1;
    private double x2;
    private double y2;

    private static final double EPSILON = 1e-10;


    /**
     * Constructs a geometry.Line object with two specified points.
     *
     * @param start The starting point of the line segment.
     * @param end   The ending point of the line segment.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;

        this.x1 = start.getX();
        this.y1 = start.getY();
        this.x2 = end.getX();
        this.y2 = end.getY();
    }

    /**
     * Constructs a geometry.Line object with specified coordinates.
     *
     * @param x1 The x-coordinate of the starting point.
     * @param y1 The y-coordinate of the starting point.
     * @param x2 The x-coordinate of the ending point.
     * @param y2 The y-coordinate of the ending point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        Point startPoint = new Point(x1, y1);
        Point endPoint = new Point(x2, y2);

        // We assign each element to its respective value
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;

        // Delegate to the other constructor using geometry.Point objects
        this.start = startPoint;
        this.end = endPoint;
    }

    /**
     * Calculates the length of the line segment.
     *
     * @return The length of the line segment.
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Finds the middle point of the line segment.
     *
     * @return The middle point of the line segment.
     */
    public Point middle() {

        // We calculate each mid point of the line
        double midX = (this.x2 + this.x1) / 2;
        double midY = (this.y2 + this.y1) / 2;

        return new Point(midX, midY);
    }

    /**
     * Gets the starting point of the line segment.
     *
     * @return The starting point of the line segment.
     */
    public Point start() {
        return new Point(this.x1, this.y1);
    }

    /**
     * Gets the ending point of the line segment.
     *
     * @return The ending point of the line segment.
     */
    public Point end() {
        return new Point(this.x2, this.y2);
    }

    /**
     * Calculates the intersection point with another line segment.
     *
     * @param other The other line segment for intersection calculation.
     * @return The intersection point if exists, or null if the lines are parallel or do not intersect.
     */
    public Point intersectionWith(Line other) {
        // Calculate the direction vectors of the two line segments
        Point thisDirection = new Point(this.x2 - this.x1, this.y2 - this.y1);
        Point otherDirection = new Point(other.x2 - other.x1, other.y2 - other.y1);

        // Calculate the cross product of the two direction vectors
        double crossProduct = thisDirection.getX() * otherDirection.getY()
                - thisDirection.getY() * otherDirection.getX();

        // If the cross product is zero, then the two lines are parallel
        if (crossProduct == 0) {
            return null;
        }

        // Calculate the differences between the start points of the two line segments
        Point startPointsDifference = new Point(other.x1 - this.x1, other.y1 - this.y1);

        // Calculate the l1 and l2 parameters of the line intersection formula
        double l1 = (startPointsDifference.getX() * otherDirection.getY()
                - startPointsDifference.getY() * otherDirection.getX())
                / crossProduct;
        double l2 = (startPointsDifference.getX() * thisDirection.getY()
                - startPointsDifference.getY() * thisDirection.getX())
                / crossProduct;
        // Check if the intersection point lies within both line segments
        if (l1 >= 0 && l1 <= 1 && l2 >= 0 && l2 <= 1) {
            double xIntersection = this.x1 + l1 * (this.x2 - this.x1);
            double yIntersection = this.y1 + l1 * (this.y2 - this.y1);
            return new Point(xIntersection, yIntersection);
        }

        return null;
    }


    /**
     * Checks if a point is within a specified range relative to a line segment defined by two points.
     *
     * @param interX The x-coordinate of the point to be checked.
     * @param interY The y-coordinate of the point to be checked.
     * @param x1     The x-coordinate of the first endpoint of the line segment.
     * @param y1     The y-coordinate of the first endpoint of the line segment.
     * @param x2     The x-coordinate of the second endpoint of the line segment.
     * @param y2     The y-coordinate of the second endpoint of the line segment.
     * @param ep     The tolerance range around the line segment.
     * @return True if the point is within the specified range of the line segment, false otherwise.
     */
    private boolean isPointInRange(double interX, double interY, double x1,
                                   double y1, double x2, double y2, double ep) {
        return ((interX + ep >= Math.min(x1, x2)
                && interX - ep <= Math.max(x1, x2))
                && (interY + ep >= Math.min(y1, y2)
                && interY - ep <= Math.max(y1, y2)));
    }


    /**
     * Calculates the slope of a line given two points.
     *
     * @param p1 The first point.
     * @param p2 The second point.
     * @return The slope of the line defined by the two points.
     * If the line is vertical, returns Double.POSITIVE_INFINITY.
     */
    public double calculateSlope(Point p1, Point p2) {

        // We calculate delta X and delta Y
        double deltaX = (p2.getX() - p1.getX());
        double deltaY = (p2.getY() - p1.getY());

        // if the delta X is 0 then it is a vertical line
        if (deltaX == 0) {
            return Double.NaN;
        } else if (deltaY == 0) {
            return Double.POSITIVE_INFINITY;
        }
        return (deltaY) / (deltaX);
    }

    /**
     * Calculates the y-intercept of a line given a point and slope.
     *
     * @param point The point on the line.
     * @param slope The slope of the line.
     * @return The y-intercept of the line.
     * If the line is vertical, returns Double.NaN.
     */
    private double findYIntercept(Point point, double slope) {

        // Vertical line has no y-intercept
        if (Double.isInfinite(slope)) {
            return Double.NaN;
        }
        if (Double.isNaN(slope)) {
            return point.getY();
        }
        return point.getY() - (slope * point.getX());
    }

    /**
     * Checks if a point (x, y) is within the line segment defined by (x1, y1) and (x2, y2).
     *
     * @param interX The x-coordinate of the point to check.
     * @param interY The y-coordinate of the point to check.
     * @param x1     The x-coordinate of the first endpoint of the line segment.
     * @param y1     The y-coordinate of the first endpoint of the line segment.
     * @param x2     The x-coordinate of the second endpoint of the line segment.
     * @param y2     The y-coordinate of the second endpoint of the line segment.
     * @return True if the point is within the line segment, false otherwise.
     */
    private boolean isPointInRange(double interX, double interY, double x1, double y1, double x2, double y2) {
        final double epsilon = 1e-10; // Adjust the epsilon value based on your requirements

        return ((interX + epsilon >= Math.min(x1, x2) && interX - epsilon <= Math.max(x1, x2))
                && (interY + epsilon >= Math.min(y1, y2) && interY - epsilon <= Math.max(y1, y2)));
    }

    /**
     * Checks if a point is within a specified range relative to a line segment.
     *
     * @param curLine        The line segment to check against.
     * @param collisionPoint The point to be checked.
     * @return True if the point is within the specified range of the line segment, false otherwise.
     */
    public static boolean isPointInRange(Line curLine, Point collisionPoint) {
        final double epsilon = 1e-10; // Adjust the epsilon value based on your requirements

        double interX = collisionPoint.getX();
        double interY = collisionPoint.getY();

        double x1 = curLine.start().getX();
        double y1 = curLine.start().getY();
        double x2 = curLine.end().getX();
        double y2 = curLine.end().getY();

        return ((interX + epsilon >= Math.min(x1, x2) && interX - epsilon <= Math.max(x1, x2))
                && (interY + epsilon >= Math.min(y1, y2) && interY - epsilon <= Math.max(y1, y2)));
    }


    /**
     * Checks if two lines are equal.
     *
     * @param other The other line to compare with.
     * @return True if the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        if (other == null) {
            return false;
        }

        // We check if the given line is equal to this line using arithmetic calculations
        boolean sameEndpoints = (((other.x1 == this.x1) && (other.y1 == this.y1))
                && ((other.x2 == this.x2) && (other.y2 == this.y2)));
        boolean swappedEndpoints = (((other.x1 == this.x2) && (other.y1 == this.y2))
                && ((other.x2 == this.x1) && (other.y2 == this.y1)));

        // return true if they are equal
        return sameEndpoints || swappedEndpoints;
    }


    /**
     * Finds the closest intersection point to the start point of the line within the specified rectangle.
     *
     * @param rect The rectangle to check for intersection points.
     * @return The closest intersection point to the start point of the line, or null if no intersections.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // Find all intersection points between the line and the rectangle
        List<Point> intersectionPoints = rect.intersectionPoints(new Line(start, end));

        if (intersectionPoints != null && !intersectionPoints.isEmpty()) {
            double minDistance = Double.MAX_VALUE;
            Point closestPoint = null;

            // Loop through each intersection point and find the closest one
            for (Point intersectionPoint : intersectionPoints) {
                double distanceToIntersection = start.distance(intersectionPoint);

                if (distanceToIntersection < minDistance) {
                    minDistance = distanceToIntersection;
                    closestPoint = intersectionPoint;
                }
            }

            return closestPoint; // Return the closest intersection point
        }

        return null; // No intersection points found
    }
}

