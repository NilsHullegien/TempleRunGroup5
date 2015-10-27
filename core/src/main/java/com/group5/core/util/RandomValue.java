package com.group5.core.util;

/**
 * Class for configurable randomized values.
 * Helpful for testing.
 * @author Nils
 *
 */
public final class RandomValue extends java.util.Random {

    /**
     * ID for the serializable.
     */
    private static final long serialVersionUID = 42L;
    /**
     * The instance of the class itself.
     */
    private static RandomValue r;

    /**
     * Constructor Private for simplicity reasons.
     */
    private RandomValue() {
        super();
    }

    /**
     * The one public way to get the Random object.
     * @return Random object with a proper seed.
     */
    public static RandomValue get() {
        if (RandomValue.r == null) {
            RandomValue.r = new RandomValue();
        }
        return RandomValue.r;
    }
}
