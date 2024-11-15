// Karam Ganaiem

package geometry;

import biuoop.DrawSurface;
import gameLogic.Game;
import gameLogic.GameEnvironment;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.Sprite;
import utilities.CollisionInfo;
import utilities.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The geometry.Ball class represents a ball in a 2D space.
 */
public class Ball implements Sprite {
    // Attributes
    private Point center;  // The center point of the ball
    private int r;         // The radius of the ball
    private Color color;   // The color of the ball
    private Velocity v;    // The velocity of the ball
    private GameEnvironment ourGame; // The game enviroment
    private List<HitListener> hitListeners;
    private int darkeningFactor = 75;

    /**
     * Constructor for creating a new geometry.Ball.
     *
     * @param center  the center point of the ball
     * @param r       the radius of the ball
     * @param color   the color of the ball
     * @param ourGamr the game environment
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment ourGamr) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.ourGame = ourGamr;
        this.hitListeners = new ArrayList<HitListener>();
    }

    // Accessors

    /**
     * Get the x-coordinate of the ball's center.
     *
     * @return the x-coordinate of the ball's center
     */
    public double getX() {
        return (int) this.center.getX();
    }

    /**
     * Get the y-coordinate of the ball's center.
     *
     * @return the y-coordinate of the ball's center
     */
    public double getY() {
        return (int) this.center.getY();
    }

    /**
     * Get the size (radius) of the ball.
     *
     * @return the size (radius) of the ball
     */
    public double getSize() {
        return this.r;
    }

    /**
     * Get the color of the ball.
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Draw the ball on the given DrawSurface.
     *
     * @param surface the DrawSurface to draw on
     */
    @Override
    public void drawOn(DrawSurface surface) {

        // Darken the color of the ball
        int darkerRed = Math.max(0, color.getRed() - darkeningFactor);
        int darkerGreen = Math.max(0, color.getGreen() - darkeningFactor);
        int darkerBlue = Math.max(0, color.getBlue() - darkeningFactor);
        Color darkerColor = new Color(darkerRed, darkerGreen, darkerBlue);

        surface.setColor(darkerColor);
        double ballXCord = center.getX();
        double ballYCord = center.getY();
        double ballRad = getSize();
        surface.fillCircle((int) ballXCord, (int) ballYCord, (int) ballRad);
    }


    /**
     * Updates the state of the ball according to the elapsed time.
     * Moves the ball one step based on its current velocity.
     * This method is typically called in each iteration of the game loop.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Set the velocity of the ball.
     *
     * @param v the velocity to set
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * Set the velocity of the ball using components (dx, dy).
     *
     * @param dx the change in x-coordinate per step
     * @param dy the change in y-coordinate per step
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * Get the velocity of the ball.
     *
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        if (v == null) {
            this.setVelocity(1, 1);
        }
        return v;
    }

    /**
     * Reverse the vertical direction of the ball's velocity.
     */
    public void reverseVerticalDirection() {
        this.getVelocity().setDy(-this.getVelocity().getDy());
    }

    /**
     * Get the center point of the ball.
     *
     * @return the center point of the ball
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * Reverse the horizontal direction of the ball's velocity.
     */
    public void reverseHorizontalDirection() {
        this.getVelocity().setDx(-this.getVelocity().getDx());
    }

    /**
     * Move the ball one step, handling collisions with screen borders.
     */

    /**
     * Moves the ball one step based on its current velocity. If a collision
     * with a collidable object along the trajectory
     * is detected, the ball's position and velocity are updated accordingly.
     */
    public void moveOneStep() {

        // Calculate the endpoint of the ball's movement
        Point endPoint = this.v.applyToPoint(this.center);
        Line trajectory = new Line(center, endPoint);

        // Check for collisions along the trajectory
        CollisionInfo possibleCollision =
                ourGame.getClosestCollision(trajectory);

//        possibleCollision.collisionObject().getCollisionRectangle().

        if (possibleCollision == null) {
            // No collision, move the ball to the endpoint
            center = endPoint;
        } else {
            // Handle collision with a collidable object
            Collidable collidable = possibleCollision.collisionObject();
            Point collisionPoint = possibleCollision.collisionPoint();

            // Move the ball to the middle of the trajectory until the collision point
            Line trajectoryToCollision = new Line(
                    center, collisionPoint);
            center = trajectoryToCollision.middle();

            // Update the velocity based on the collision
            v = collidable.hit(this, collisionPoint, v);
        }
    }

    /**
     * Adds the ball as a sprite to the specified game. The ball will be displayed and updated in the game.
     *
     * @param g The game to which the ball is added.
     */
    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * Removes the ball from the specified game.
     *
     * @param g The game from which the ball is removed.
     */
    public void removeFromGame(Game g) {
        g.removeSprite(this);
    }

    /**
     * Sets the color of the ball to the specified color.
     *
     * @param newColor The new color of the ball.
     */
    public void setColor(Color newColor) {
        this.color = newColor;
    }
}

