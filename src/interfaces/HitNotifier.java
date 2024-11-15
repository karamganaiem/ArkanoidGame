// Karam Ganaiem

package interfaces;

/**
 * The HitNotifier interface represents an object that can notify listeners of hit events.
 */
public interface HitNotifier {

    /**
     * Add a HitListener to the list of listeners to hit events.
     *
     * @param hl The HitListener to be added.
     */
    void addHitListener(HitListener hl);

    /**
     * Remove a HitListener from the list of listeners to hit events.
     *
     * @param hl The HitListener to be removed.
     */
    void removeHitListener(HitListener hl);
}
