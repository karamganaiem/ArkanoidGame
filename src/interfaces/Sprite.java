// Karam Ganaiem

package interfaces;

import biuoop.DrawSurface;
import gameLogic.Game;

/**
 * The interfaces.Sprite interface represents objects that can be drawn on a screen.
 */
public interface Sprite {
    /**
     * Draws the sprite on the given DrawSurface.
     *
     * @param d The DrawSurface to draw on.
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that a unit of time has passed.
     */
    void timePassed();

    /**
     * Adds the sprite to the specified game.
     *
     * @param g The game to which the sprite will be added.
     */
    void addToGame(Game g);
}
