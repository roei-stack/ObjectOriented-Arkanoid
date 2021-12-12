package geometry;

/**
 * @author Roei Cohen
 * ID 325714152
 * the counter is used to keep track of scores, remaining balls, and remaining blocks.
 */
public class Counter {

    private int count;

    /** @param c constructs a counter with the following default value*/
    public Counter(int c) {
        count = c;
    }

    /**@param number add number to current count. */
    public void increase(int number) {
        count += number;
    }

    /**@param number subtract number from current count. */
    public void decrease(int number) {
        count -= number;
    }

    /**@return the current count*/
    public int getValue() {
        return count;
    }
}