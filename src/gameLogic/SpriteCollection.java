// Karam Ganaiem

package gameLogic;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.util.LinkedList;
import java.util.List;

/**
 * The gameLogic.SpriteCollection class represents a collection of sprites in a game.
 */
public class SpriteCollection {
    private List<Sprite> gameSprites;

    /**
     * Creates a new gameLogic.SpriteCollection with an empty list of sprites.
     */
    public SpriteCollection() {
        this.gameSprites = new LinkedList<>();
    }

    /**
     * Adds a sprite to the collection.
     *
     * @param s The sprite to be added.
     */
    public synchronized void addSprite(Sprite s) {
        gameSprites.add(s);
    }

    /**
     * Removes a sprite from the collection.
     *
     * @param s The sprite to be removed.
     */
    public synchronized void removeSprite(Sprite s) {
        gameSprites.remove(s);
    }

    /**
     * Calls the timePassed() method on all sprites in the collection.
     */
    public void notifyAllTimePassed() {
        // Create a copy of the list to avoid ConcurrentModificationException
        List<Sprite> spritesCopy;
        synchronized (this) {
            spritesCopy = new LinkedList<>(gameSprites);
        }

        // Iterate over the copied list and call timePassed() on each sprite
        for (Sprite sprite : spritesCopy) {
            sprite.timePassed();
        }
    }

    /**
     * Calls the drawOn(d) method on all sprites in the collection.
     *
     * @param d The DrawSurface on which the sprites will be drawn.
     */
    public void drawAllOn(DrawSurface d) {
        // Create a copy of the list to avoid ConcurrentModificationException
        List<Sprite> spritesCopy;
        synchronized (this) {
            spritesCopy = new LinkedList<>(gameSprites);
        }

        // Iterate over the copied list and call drawOn(d) on each sprite
        for (Sprite sprite : spritesCopy) {
            sprite.drawOn(d);
        }
    }
}
