// Karam Ganaiem

package gameLogic;

import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Collidable;
import utilities.CollisionInfo;

import java.util.LinkedList;
import java.util.List;

/**
 * The gameLogic.GameEnvironment class represents the environment of the game, containing collidable objects.
 */
public class GameEnvironment {
    private List<Collidable> gameCollidables;

    /**
     * Constructs a new gameLogic.GameEnvironment with an empty list of collidables.
     */
    public GameEnvironment() {
        this.gameCollidables = new LinkedList<>();
    }

    /**
     * Adds the given collidable to the environment.
     *
     * @param collidable The collidable to be added.
     */
    public void addCollidable(Collidable collidable) {
        this.gameCollidables.add(collidable);
    }

    /**
     * Removes a collidable object from the game.
     *
     * @param collidable the collidable object to be removed
     */
    public void removeCollidable(Collidable collidable) {
        this.gameCollidables.remove(collidable);
    }


    /**
     * Finds the closest collision point along the given trajectory.
     *
     * @param trajectory The trajectory to find collisions on.
     * @return The utilities.CollisionInfo of the closest collision point, or null if no collisions were found.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<CollisionInfo> collisionInfos = new LinkedList<>();

        // Find all collision points along the trajectory
        for (Collidable collidable : gameCollidables) {
            Rectangle collidableRectangle = collidable.getCollisionRectangle();
            Point collisionPoint = trajectory.closestIntersectionToStartOfLine(collidableRectangle);

            if (collisionPoint != null) {
                CollisionInfo collisionInfo = new CollisionInfo(collisionPoint, collidable);
                collisionInfos.add(collisionInfo);
            }
        }

        // If no collision points were found, return null
        if (collisionInfos.isEmpty()) {
            return null;
        }

        // Find the closest collision point
        Point start = trajectory.start();
        CollisionInfo closestCollision = collisionInfos.get(0);
        double closestDistance = closestCollision.collisionPoint().distance(start);

        for (CollisionInfo collision : collisionInfos) {
            double currentDistance = collision.collisionPoint().distance(start);
            if (currentDistance < closestDistance) {
                closestCollision = collision;
                closestDistance = currentDistance;
            }
        }

        return closestCollision;
    }


}
