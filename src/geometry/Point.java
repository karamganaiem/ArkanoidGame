// Karam Ganaiem

package geometry;

/**
 * The geometry.Point class represents a point in a 2D coordinate system.
 * It encapsulates the x and y coordinates of the point and provides
 * methods for calculating the distance between points and checking equality.
 */
public class Point {
    /**
     * The x-coordinate of the point.
     */
    private double xCoordinate;

    /**
     * The y-coordinate of the point.
     */
    private double yCoordinate;

    /**
     * Constructs a geometry.Point object with specified coordinates.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     */
    public Point(double x, double y) {
        this.xCoordinate = x;
        this.yCoordinate = y;
    }

    /**
     * Calculates the distance between this point and another point.
     *
     * @param other The other point to calculate the distance to.
     * @return The distance between this point and the other point.
     */
    public double distance(Point other) {
        if (other == null) {
            return 0;
        }

        double distanceAnswer, xDifference, yDifference;

        // Calculate the differences in x and y coordinates
        xDifference = this.xCoordinate - other.xCoordinate;
        yDifference = this.yCoordinate - other.yCoordinate;

        // Use the Pythagorean theorem to calculate the distance
        distanceAnswer = Math.sqrt((xDifference * xDifference) + (yDifference * yDifference));

        return distanceAnswer;
    }

    /**
     * Checks if this point is equal to another point.
     *
     * @param other The other point to compare with.
     * @return True if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        // Return false if the other point is null
        if (other == null) {
            return false;
        } else {
            // Check equality based on x and y coordinates
            return (other.xCoordinate == this.xCoordinate) && (other.yCoordinate == this.yCoordinate);
        }
    }

    /**
     * Gets the x-coordinate of this point.
     *
     * @return The x-coordinate of this point.
     */
    public double getX() {
        return this.xCoordinate;
    }

    /**
     * Gets the y-coordinate of this point.
     *
     * @return The y-coordinate of this point.
     */
    public double getY() {
        return this.yCoordinate;
    }

    /**
     * Sets the x-coordinate of the point to the specified value.
     *
     * @param newX The new x-coordinate value.
     */
    public void setX(double newX) {
        this.xCoordinate = newX;
    }

    /**
     * Sets the y-coordinate of the point to the specified value.
     *
     * @param newY The new y-coordinate value.
     */
    public void setY(double newY) {
        this.yCoordinate = newY;
    }

}
