package moe.rangho.jialoguescript.util;

/**
 * Represents a pair of data of the same type.
 * @param <T> The type of eah data.
 */
public class Pair<T> {

    public final T val1;

    public final T val2;

    public Pair(T val1, T val2) {
        this.val1 = val1;
        this.val2 = val2;
    }

    /**
     * Performs a "deep" comparison of two Pair object.
     * @param that object to compare against.
     * @return true if all elements are the same, false if not.
     */
    @Override
    public boolean equals(Object that) {
        if (that instanceof Pair) {
            Pair thatPair = (Pair)that;
            return this.val1 == thatPair.val1
                   && this.val2 == thatPair.val2;
        }

        return false;
    }

    /**
     * Returns a String representation of this pair.
     * @return String representation in form of <code>(val1, val2)</code>.
     */
    @Override
    public String toString() {
        return "(" + val1.toString() + ", " + val2.toString() + ")";
    }
}
