// Karam Ganaiem

package utilities;

import geometry.Ball;
import geometry.Block;
import interfaces.HitListener;

/**
 * The ScoreTrackingListener class represents a listener that tracks the player's score.
 */
public class ScoreTrackingListener implements HitListener {
    /**
     * The current score.
     */
    private Counter currentScore;

    /**
     * Constructs a new ScoreTrackingListener with the specified score counter.
     *
     * @param scoreCounter The score counter to track.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Constructs a new ScoreTrackingListener with the score initialized to zero.
     */
    public ScoreTrackingListener() {
        this.currentScore = new Counter();
    }

    /**
     * Constructs a new ScoreTrackingListener with the score initialized to the specified value.
     *
     * @param numberToAdd The value to initialize the score to.
     */
    public ScoreTrackingListener(int numberToAdd) {
        this.currentScore = new Counter();
        this.currentScore.increase(numberToAdd);
    }

    /**
     * Returns the current score.
     *
     * @return The current score.
     */
    public int getValue() {
        return this.currentScore.getValue();
    }

    /**
     * Increases the score by 5 when a block is hit.
     *
     * @param beingHit The block being hit.
     * @param hitter   The ball that hits the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}
