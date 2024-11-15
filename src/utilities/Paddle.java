// Karam Ganaiem

package utilities;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gameLogic.Game;
import geometry.Ball;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Collidable;
import interfaces.Sprite;


import java.awt.Color;

/**
 * The utilities.Paddle class represents a paddle in the game.
 */
public class Paddle implements Sprite, Collidable {
    private KeyboardSensor keyIn;
    private Rectangle shape;
    private Color color;
    private double velocity;


    /**
     * Constructs a new utilities.Paddle.
     *
     * @param keyIn    The keyboard sensor for user input.
     * @param shape    The shape (rectangle) of the paddle.
     * @param color    The color of the paddle.
     * @param velocity The velocity of the paddle.
     */
    public Paddle(KeyboardSensor keyIn, Rectangle shape, Color color, double velocity) {
        this.color = color;
        this.keyIn = keyIn;
        this.shape = shape;
        this.velocity = velocity;
    }


    /**
     * Moves the paddle to the left.
     */
    public void moveLeft() {
        movePaddle(this.shape, -7);
    }

    /**
     * Moves the paddle to the right.
     */
    public void moveRight() {
        movePaddle(this.shape, 7);
    }

    /**
     * Handles the paddle's movement based on the user's keyboard input.
     * If the left arrow key is pressed, the paddle moves left.
     * If the right arrow key is pressed, the paddle moves right.
     */
    @Override
    public void timePassed() {
        if (keyIn.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyIn.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }


    /**
     * Draws the paddle on the given DrawSurface.
     *
     * @param d The DrawSurface on which to draw the paddle.
     */
    @Override
    public void drawOn(DrawSurface d) {
        int xCord = (int) this.shape.getUpperLeft().getX();
        int yCord = (int) this.shape.getUpperLeft().getY();
        int drawWidth = (int) this.shape.getWidth();
        int drawHeight = (int) this.shape.getHeight();
        d.setColor(this.color);
        d.fillRectangle(xCord, yCord, drawWidth, drawHeight);
    }

    /**
     * Returns the collision rectangle representing the paddle.
     *
     * @return The collision rectangle representing the paddle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }

    /**
     * Handles a collision with the paddle, updating the ball's velocity and position accordingly.
     *
     * @param collisionPoint  The point of collision.
     * @param currentVelocity The current velocity of the ball.
     * @return The updated velocity after the collision.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double hitX = collisionPoint.getX();
        double paddleWidth = this.shape.getWidth() / 5;
        double seg0 = this.shape.getUpperLeft().getX();


        // We calculate each line segment of the paddle
        double seg1 = seg0 + paddleWidth * 1;
        double seg2 = seg0 + paddleWidth * 2;
        double seg3 = seg0 + paddleWidth * 3;
        double seg4 = seg0 + paddleWidth * 4;
        double seg5 = seg0 + paddleWidth * 5;

        double curSpeed = Velocity.calculateVectorSpeed(currentVelocity.getDx(), currentVelocity.getDy());

        if (hitX >= seg0 && hitX <= seg1) {
            currentVelocity = Velocity.fromAngleAndSpeed(300, curSpeed);
        } else if (hitX >= seg1 && hitX <= seg2) {
            currentVelocity = Velocity.fromAngleAndSpeed(330, curSpeed);
        } else if (hitX >= seg2 && hitX <= seg3) {
            currentVelocity.setDy(-currentVelocity.getDy());

            // Adjust ball's position to be above the paddle
            collisionPoint.setY(this.shape.getUpperLeft().getY() - 1);
        } else if (hitX >= seg3 && hitX <= seg4) {
            currentVelocity = Velocity.fromAngleAndSpeed(30, curSpeed);
        } else if (hitX >= seg4 && hitX <= seg5) {
            currentVelocity = Velocity.fromAngleAndSpeed(60, curSpeed);

            // Adjust ball's position to be below the paddle
            collisionPoint.setY(this.shape.getUpperLeft().getY() + this.shape.getHeight() + 1);
        } else if (hitX > seg5) {
            // Adjust ball's position to the right of the paddle
            collisionPoint.setX(seg5 + 1);

            if (currentVelocity.getDx() > 0) {
                currentVelocity.setDx(-currentVelocity.getDx());
            }
        } else if (hitX < seg0) {
            // Adjust ball's position to the left of the paddle
            collisionPoint.setX(seg0 - 1);

            if (currentVelocity.getDx() < 0) {
                currentVelocity.setDx(-currentVelocity.getDx());
            }
        }
        return currentVelocity;
    }


    /**
     * Adds the paddle to the game by registering it as a sprite and a collidable.
     *
     * @param g The game to which the paddle is added.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }


    /**
     * Moves the paddle horizontally based on the specified step.
     *
     * @param shape The current shape (rectangle) of the paddle.
     * @param step  The step size for the movement.
     */
    public void movePaddle(Rectangle shape, double step) {
        double newX = shape.getUpperLeft().getX() + step;

        // Check if the new X-coordinate is beyond the right edge
        if (newX > 685) {
            // Move to the left side
            this.shape = new Rectangle(new geometry.Point(25, 560), 90, 15);
        } else if (newX < 25) {
            // Move to the right side
            this.shape = new Rectangle(new geometry.Point(685, 560), 90, 15);
        } else {
            // Regular movement within the screen boundaries
            this.shape = new Rectangle(new geometry.Point(newX, 560), 90, 15);
        }
    }
}