// Karam Ganaiem

package gameLogic;

import geometry.Ball;
import geometry.Block;
import interfaces.HitListener;
import utilities.Counter;

/**
 * The {@code BlockRemover} class is responsible for removing blocks from the game and updating the block count.
 * It implements the {@link interfaces.HitListener} interface to handle block-ball collisions.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Constructs a new {@code BlockRemover} with the specified game and counter.
     *
     * @param game            The game from which blocks are removed.
     * @param remainingBlocks The counter representing the remaining blocks.
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Handles the event when a block is hit by a ball.
     * Removes the block from the game and updates the remaining blocks count.
     * Also removes this listener from the block being removed.
     *
     * @param beingHit The block being hit.
     * @param hitter   The ball that hit the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // Remove the block from the game
        beingHit.removeFromGame(game);

        // Remove this listener from the block
        beingHit.removeHitListener(this);

        // Decrease the remaining blocks count
        game.decreaseBlockCount();
        remainingBlocks.decrease(1);
    }
}
