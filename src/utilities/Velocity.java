// Karam Ganaiem

package utilities;

import geometry.Point;

/**
 * The utilities.Velocity class represents a 2D vector for movement in a plane.
 */
public class Velocity {
    /**
     * The change in the x-coordinate (dx).
     */
    private double dx;

    /**
     * The change in the y-coordinate (dy).
     */
    private double dy;

    /**
     * Constructs a utilities.Velocity object with specified changes in x and y coordinates.
     *
     * @param dx The change in the x-coordinate.
     * @param dy The change in the y-coordinate.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Takes a point with position (x, y) and returns a new point
     * with position (x + dx, y + dy).
     *
     * @param p The point to which the velocity is applied.
     * @return A new point after applying the velocity.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * Creates a new utilities.Velocity instance from an angle and speed.
     *
     * @param angle The angle in degrees.
     * @param speed The speed of the velocity.
     * @return A new utilities.Velocity instance based on the given angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.cos(Math.toRadians(angle - 90));
        double dy = speed * Math.sin(Math.toRadians(angle - 90));
        return new Velocity(dx, dy);
    }

    /**
     * Sets the change in the x-coordinate (dx) of the velocity.
     *
     * @param newDx The new value for dx.
     */
    public void setDx(double newDx) {
        this.dx = newDx;
    }

    /**
     * Sets the change in the y-coordinate (dy) of the velocity.
     *
     * @param newDy The new value for dy.
     */
    public void setDy(double newDy) {
        this.dy = newDy;
    }

    /**
     * Gets the change in the x-coordinate (dx) of the velocity.
     *
     * @return The change in the x-coordinate.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gets the change in the y-coordinate (dy) of the velocity.
     *
     * @return The change in the y-coordinate.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Calculates the speed of a 2D vector given its components.
     *
     * @param dx The x-component of the vector.
     * @param dy The y-component of the vector.
     * @return The magnitude (speed) of the vector.
     */
    public static double calculateVectorSpeed(double dx, double dy) {
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }
}

