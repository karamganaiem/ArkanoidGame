// Karam Ganaiem

package utilities;

/**
 * The Counter class represents a simple counter that can be incremented or decremented.
 */
public class Counter {
    private int currentCounter;

    /**
     * Constructs a new Counter with an initial value.
     *
     * @param initialValue The initial value of the counter.
     */
    public Counter(int initialValue) {
        this.currentCounter = initialValue;
    }

    /**
     * Constructs a new Counter with an initial value of 0.
     */
    public Counter() {
        this.currentCounter = 0;
    }

    /**
     * Increases the counter by a specified number.
     *
     * @param number The number to increase the counter by.
     */
    public void increase(int number) {
        this.currentCounter += number;
    }

    /**
     * Decreases the counter by a specified number.
     *
     * @param number The number to decrease the counter by.
     */
    public void decrease(int number) {
        this.currentCounter -= number;
    }

    /**
     * Gets the current value of the counter.
     *
     * @return The current value of the counter.
     */
    public int getValue() {
        return currentCounter;
    }
}
