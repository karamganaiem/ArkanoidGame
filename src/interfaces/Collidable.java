// Karam Ganaiem

package interfaces;

import biuoop.DrawSurface;
import geometry.Ball;
import geometry.Point;
import geometry.Rectangle;
import utilities.Velocity;

/**
 * An interface representing a collidable object in a game.
 */
public interface Collidable {

    /**
     * Returns the collision rectangle of the object.
     *
     * @return The collision rectangle of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object that a collision occurred at the specified collision point with a given velocity.
     * Returns the new velocity expected after the hit based on the force the object inflicted.
     *
     * @param hitter          The ball that hit the collidable object.
     * @param collisionPoint  The point of collision.
     * @param currentVelocity The current velocity of the colliding object.
     * @return The updated velocity after the collision.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * Draws the collidable object on the given draw surface.
     *
     * @param surface The draw surface on which the object is drawn.
     */
    void drawOn(DrawSurface surface);
}
