// Karam Ganaiem

package utilities;

import geometry.Point;
import interfaces.Collidable;

/**
 * Represents information about a collision.
 */
public class CollisionInfo {
    private Collidable collisionObject;
    private Point collisionPoint;

    /**
     * Constructs a new utilities.CollisionInfo with the specified collision point and collidable object.
     *
     * @param collisionPoint   The point at which the collision occurs.
     * @param collisionObject  The collidable object involved in the collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionObject = collisionObject;
        this.collisionPoint = collisionPoint;
    }

    /**
     * Gets the collision point.
     *
     * @return The point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Gets the collidable object involved in the collision.
     *
     * @return The collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
