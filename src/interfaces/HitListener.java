// Karam Ganaiem

package interfaces;

import geometry.Ball;
import geometry.Block;

/**
 * The HitListener interface represents an object that listens for hit events.
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit The block object that was hit.
     * @param hitter   The ball object that caused the hit.
     */
    void hitEvent(Block beingHit, Ball hitter);
}

