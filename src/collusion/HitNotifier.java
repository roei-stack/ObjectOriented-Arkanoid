package collusion;

/**
 * @author Roei Cohen
 * ID 325714152
 * hit notifier is implemented by object whose intrested in storing hit listeners
 */
public interface HitNotifier {

    /**@param hl Add hl as a listener to hit events. */
    void addHitListener(HitListener hl);

    /**@param hl Remove hl from the list of listeners to hit events. */
    void removeHitListener(HitListener hl);
}
