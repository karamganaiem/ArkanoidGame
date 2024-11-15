// Karam Ganaiem

package geometry;

import biuoop.DrawSurface;
import gameLogic.Game;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;
import utilities.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a block that can be drawn on the screen, has collision properties,
 * and can be part of the game as a sprite.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle shape;
    private Color shapeColor;
    private static final int DARKENING_FACTOR = 75;
    private List<HitListener> ourHitListeners;


    /**
     * Constructs a block with the specified shape and color.
     *
     * @param shape      The shape (rectangle) of the block.
     * @param shapeColor The color of the block.
     */
    public Block(Rectangle shape, Color shapeColor) {
        this.shape = shape;
        this.shapeColor = shapeColor;

        this.ourHitListeners = new ArrayList<>();
    }

    /**
     * Draws the block on the given draw surface.
     *
     * @param drawSurface The draw surface on which the block is drawn.
     */
    @Override
    public void drawOn(DrawSurface drawSurface) {


        if (this.shapeColor == Color.pink) {
            return;
        }

        int xCord = (int) this.shape.getUpperLeft().getX();
        int yCord = (int) this.shape.getUpperLeft().getY();
        int drawWidth = (int) this.shape.getWidth();
        int drawHeight = (int) this.shape.getHeight();

        // Darken the color of the block
        int darkerRed = Math.max(0, this.shapeColor.getRed() - DARKENING_FACTOR);
        int darkerGreen = Math.max(0, this.shapeColor.getGreen() - DARKENING_FACTOR);
        int darkerBlue = Math.max(0, this.shapeColor.getBlue() - DARKENING_FACTOR);
        Color darkerColor = new Color(darkerRed, darkerGreen, darkerBlue);

        drawSurface.setColor(darkerColor);
        drawSurface.fillRectangle(xCord, yCord, drawWidth, drawHeight);

        drawSurface.setColor(Color.black);
        drawSurface.drawRectangle(xCord, yCord, drawWidth, drawHeight);
    }

    /**
     * Does nothing for the block when time passes.
     */
    @Override
    public void timePassed() {
        // Empty implementation for a static block
    }


    /**
     * Gets the collision rectangle of the block.
     *
     * @return The collision rectangle of the block.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }


    /**
     * Updates the velocity of an object that collides with the block based on the collision point.
     *
     * @param collisionPoint  The point of collision.
     * @param currentVelocity The current velocity of the colliding object.
     * @return The updated velocity after the collision.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dX = currentVelocity.getDx();
        double dY = currentVelocity.getDy();

        double collisionX = collisionPoint.getX();
        double collisionY = collisionPoint.getY();

        double shapeUpperLeftX = shape.getUpperLeft().getX();
        double shapeUpperLeftY = shape.getUpperLeft().getY();

        double shapeWidth = shape.getWidth();
        double shapeHeight = shape.getHeight();


        // Check and reverse vertical direction if there's a collision
        if ((collisionY >= shapeUpperLeftY + shapeHeight) || (collisionY <= shapeUpperLeftY)) {
            dY = -dY;
        }

        // Check and reverse horizontal direction if there's a collision
        if ((collisionX >= shapeUpperLeftX + shapeWidth) || (collisionX <= shapeUpperLeftX)) {
            dX = -dX;
        }

        // If ball's color matches block's color, do nothing and continue moving
        if (ballColorMatch(hitter)) {
            return new Velocity(dX, dY);
        } else { // geometry.Ball's color does not match block's color

            if (shapeColor == Color.white) {
                this.notifyHit(hitter);
            }

            if (shapeColor != Color.gray && shapeColor != Color.darkGray) {
                hitter.setColor(shapeColor);
                this.notifyHit(hitter);
            }
            return new Velocity(dX, dY);
        }
    }

    /**
     * Adds the block to the game by adding it as both a sprite and a collidable.
     *
     * @param g The game to which the block is added.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Checks if the color of this block matches the color of the specified ball.
     *
     * @param ball The ball to compare colors with.
     * @return true if the colors match, false otherwise.
     */
    public boolean ballColorMatch(Ball ball) {
        Color cur1 = ball.getColor();
        Color cur2 = this.shapeColor;
        return cur2 == cur1;
    }

    /**
     * Removes this block from the specified game, including both sprite and collidable removal.
     *
     * @param game The game from which to remove this block.
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    /**
     * Adds a hit listener to this block.
     *
     * @param hl The hit listener to add.
     */
    @Override
    public void addHitListener(HitListener hl) {
        ourHitListeners.add(hl);
    }

    /**
     * Removes a hit listener from this block.
     *
     * @param hl The hit listener to remove.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        ourHitListeners.remove(hl);
    }


    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(ourHitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}



