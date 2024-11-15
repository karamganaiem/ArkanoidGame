// Karam Ganaiem

package gameLogic;

import geometry.Ball;
import geometry.Block;
import interfaces.HitListener;
import utilities.Counter;

/**
 * The {@code BallRemover} class removes balls from the game when they hit a block.
 * This class implements the {@code HitListener} interface to handle ball-block collisions.
 */
public class BallRemover implements HitListener {
    /**
     * The game from which balls are removed.
     */
    private Game game;

    /**
     * The counter representing the remaining balls.
     */
    private Counter remainingBalls;

    /**
     * Constructs a new {@code BallRemover} with the specified game and counter.
     *
     * @param game           The game from which balls are removed.
     * @param remainingBalls The counter representing the remaining balls.
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * Removes the ball from the game when it hits a block and updates the remaining balls count.
     *
     * @param beingHit The block being hit.
     * @param hitter   The ball that hit the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // Remove the ball from the game
        hitter.removeFromGame(game);

        // Decrease the remaining balls count
        game.decreaseBallCount();
        remainingBalls.decrease(1);
    }
}
